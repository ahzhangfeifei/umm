package com.umm.wfm.base.redis;

import com.umm.ats.core.cache.CacheWorker;
import com.umm.ats.core.cache.support.KeyLock;
import com.umm.ats.core.redis.JedisTemplate;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 按key进行锁的缓存worker，用在write方法可能执行较长，且很多其他模块调用的情况下。避免因为write锁住所有读线程。
 * 做为key的Z，必须实现equal和hash, 并且.toString()方法保证是不同key生成不同的值，避免不同的key锁是同一个。
 *
 * Created by zhangff on 2016/1/6.
 */
public abstract class KeyBlockedCacheWorker<T, Z> implements CacheWorker<T, Z> {

    private final KeyLock<Z> keyLock = new KeyLock<Z>();

    @Resource
    protected JedisTemplate jedisTemplate;

    private AtomicLong hits = new AtomicLong(0);

    private AtomicLong misses = new AtomicLong(0);

    /**
     * 读Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出读取结果
     */
    protected T read(Z z) { // NOSONAR
        throw new IllegalArgumentException("read 方法未定义");
    }

    /**
     * 回源并写Redis缓存的实现
     *
     * @param z 输入参数
     * @return 输出回源结果
     */
    protected T write(Z z) { // NOSONAR
        throw new IllegalArgumentException("write 方法未定义");
    }

    /**
     * 单条回源后，立即执行方法入口
     *
     * @param t 输入对象
     * @return 输出对象
     */
    protected T beforeReturn(T t) {
        return t;
    }

    @Override
    public final T find(Z z) {
        if (z == null) {
            return null;
        }
        boolean lockedFail = false;
        do {
            if (lockedFail) {
                lockedFail = false;
                if (!sleep()) return null;
            }
            keyLock.lock(z);
            try {
                T t = read(z);
                if (t == null) {
                    String lockName = getLockKey(z);
                    if (jedisTemplate.acquireLock(lockName)) {
                        try {
                            misses.incrementAndGet();
                            t = write(z);
                        } finally {
                            jedisTemplate.releaseLock(lockName);
                        }
                    } else {
                        lockedFail = true;
                        continue;
                    }
                } else {
                    hits.incrementAndGet();
                }
                return beforeReturn(t);
            } finally {
                keyLock.unlock(z);
            }
        } while (true);
    }

    @Override
    public final List<T> batchFind(List<Z> list) {
        throw new IllegalArgumentException("batchFind 方法未定义");
    }

    private String getLockKey(Z z) {
        return "k_lock_" + this.getClass().getSimpleName() + z;
    }

    private boolean sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ex) {
            return false;
        }
        return true;
    }

    /**
     * 命中数
     * @return 命中数
     */
    @ManagedAttribute(description = "命中数")
    public long getHits() {
        return hits.get();
    }

    /**
     * miss数
     * @return miss数
     */
    @ManagedAttribute(description = "MISS数")
    public long getMisses() {
        return misses.get();
    }
}
