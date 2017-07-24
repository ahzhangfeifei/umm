package com.umm.wfm.base.rest;

import com.umm.ats.core.tracer.annotation.CommandTrace;
import com.umm.ats.core.tracer.pojo.TraceCmd;
import com.umm.wfm.security.log.LogUtil;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * REST接口调用统一服务组件
 *
 * Created by zhangff on 2016/3/12.
 */
public class BinRestClient implements InitializingBean {

    private static final Logger LOGGER = LogUtil.getLogger(BinRestClient.class);

    private static OkHttpClient okHttpClient;

    private int connectTimeout = 10;
    private int socketTimeout = 10;

    private RestCodec restCodec;

    @CommandTrace(name = TraceCmd.REST_CALL, type = "#url", input = "#requestData")
    public Object query(String url, Object requestData) throws IOException {
        return queryInner(url, HttpMethod.POST, requestData, restCodec, socketTimeout);
    }

    @CommandTrace(name = TraceCmd.REST_CALL, type = "#url", input = "#requestData")
    public Object query(String url, HttpMethod httpMethod, Object requestData) throws IOException {
        return queryInner(url, httpMethod, requestData, restCodec, socketTimeout);
    }

    @CommandTrace(name = TraceCmd.REST_CALL, type = "#url", input = "#requestData")
    public Object query(String url, HttpMethod httpMethod, Object requestData, int socketTimeout) throws IOException {
        return queryInner(url, httpMethod, requestData, restCodec, socketTimeout);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // build ok httpClient
        if (okHttpClient == null) {

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

            if (0 < connectTimeout) {
                clientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS);
            }
            if (0 < socketTimeout) {
                clientBuilder.readTimeout(socketTimeout, TimeUnit.SECONDS);
                clientBuilder.writeTimeout(socketTimeout, TimeUnit.SECONDS);
            }
            clientBuilder.retryOnConnectionFailure(true);
            okHttpClient = clientBuilder.build();
        }
    }

    private Object queryInner(String url, HttpMethod httpMethod, Object input, RestCodec restCodec, int socketTimeout) throws IOException {
        // get method query param;
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(url).newBuilder();

        // headers
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(httpUrlBuilder.build());

        // post or get method
        if (httpMethod == HttpMethod.GET) {
            requestBuilder.get();
        } else if (httpMethod == HttpMethod.POST) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(restCodec.mediaType()), restCodec.encode(input));
            requestBuilder.post(requestBody);
        } else {
            throw new IOException("不支持的httpMethod:" + httpMethod);
        }

        Response response = okHttpClient.newBuilder()
                .readTimeout(socketTimeout, TimeUnit.SECONDS)
                .writeTimeout(socketTimeout, TimeUnit.SECONDS)
                .build().newCall(requestBuilder.build()).execute();

        if (response.isSuccessful()) {
            return restCodec.decode(response.body().bytes());
        } else {
            throw new IOException("请求失败：Unexpected code " + response);
        }
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static void setOkHttpClient(OkHttpClient okHttpClient) {
        BinRestClient.okHttpClient = okHttpClient;
    }

    public RestCodec getRestCodec() {
        return restCodec;
    }

    public void setRestCodec(RestCodec restCodec) {
        this.restCodec = restCodec;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        okHttpClient = okHttpClient.newBuilder().connectTimeout(connectTimeout, TimeUnit.SECONDS).build();
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        okHttpClient = okHttpClient.newBuilder()
                .readTimeout(socketTimeout, TimeUnit.SECONDS)
                .writeTimeout(socketTimeout, TimeUnit.SECONDS)
                .build();
    }
}
