package com.umm.wfm.security.log;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * User: lizhimu
 * Date: 2017/6/19
 * Time: 12:17
 * Desc: 敏感信息过滤工具类
 */

@Component
public class SensitiveMsgFliterUtil {

    //@Value(value = "isLogFilterSwitchOn")
    private static boolean logFilterSwtichOn = true;

    //@Value(value = "logFilterKeys")
    private static String logFilterKeys = "abc,bbb,ddds,地址";

    public static String filterSensitiveMsg(final String message) {

        String msg = new String(message);
        if (logFilterSwtichOn) {
            //处理字符串
            for (SensitiveKeysEnum sensitiveKey : SensitiveKeysEnum.getAll()) {

                String[] keyArr = sensitiveKey.getSensitiveKey().split(",");
                for (String key : keyArr) {
                    // 找key
                    int index = -1;
                    do {
                        index = msg.indexOf(key, index + 1);
                        if (index != -1) {
                            // 判断key是否为单词字符
                            if (isWordChar(msg, key, index)) {
                                continue;
                            }
                            // 确定是单词无疑....................................
                            // 寻找值的开始位置.................................
                            int valueStart = getValueStartIndex(msg, index + key.length());

                            //查找值的结束位置（逗号，分号）........................
                            int valueEnd = getValuEndEIndex(msg, valueStart);

                            // 对获取的值进行脱敏
                            String subStr = msg.substring(valueStart, valueEnd);
                            subStr = desensitize(subStr, sensitiveKey);
                            ///////////////////////////
                            msg = msg.substring(0, valueStart) + subStr + msg.substring(valueEnd);
                        }
                    } while (index != -1);

                }
            }
        }

        return msg;
    }


    private static Pattern pattern = Pattern.compile("[0-9a-zA-Z]");

    /***
     * 检索key
     * @param msg
     * @param key
     * @param index
     * @return
     */
    private static boolean isWordChar(String msg, String key, int index) {

        // 必须确定key是一个单词............................
        if (index != 0) { //判断key前面一个字符
            char preCh = msg.charAt(index - 1);
            Matcher match = pattern.matcher(preCh + "");
            if (match.matches()) {
                return true;
            }
        }
        //判断key后面一个字符
        char nextCh = msg.charAt(index + key.length());
        Matcher match = pattern.matcher(nextCh + "");
        if (match.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 获取value值的开始位置
     *
     * @param msg        要查找的字符串
     * @param valueStart 查找的开始位置
     * @return
     */
    private static int getValueStartIndex(String msg, int valueStart) {
        // 寻找值的开始位置.................................
        do {
            char ch = msg.charAt(valueStart);
            if (ch == ':' || ch == '=') { // key 与 value的分隔符
                valueStart++;
                ch = msg.charAt(valueStart);
                if (ch == '"') {
                    valueStart++;
                }
                break;    //找到值的开始位置
            } else {
                valueStart++;
            }

        } while (true);
        return valueStart;
    }


    /***
     * 获取key 结束值位置
     * @param msg
     * @param valueEnd
     * @return
     */
    private static int getValuEndEIndex(String msg, int valueEnd) {

        do {
            if (valueEnd == msg.length()) {
                break;
            }
            char ch = msg.charAt(valueEnd);

            if (ch == '"') { // 引号时，判断下一个值是结束，分号还是逗号决定是否为值的结束
                if (valueEnd + 1 == msg.length()) {
                    break;
                }
                char nextCh = msg.charAt(valueEnd + 1);
                if (nextCh == ';' || nextCh == ',' || nextCh == '}' || nextCh == ']') {
                    // 去掉前面的 \  处理这种形式的数据 "account_num\\\":\\\"6230958600001008\\\"
                    while (valueEnd > 0) {
                        char preCh = msg.charAt(valueEnd - 1);
                        if (preCh != '\\') {
                            break;
                        }
                        valueEnd--;
                    }
                    break;
                } else {
                    valueEnd++;
                }
            } else if (ch == ';' || ch == ',') {
                break;
            } else {
                valueEnd++;
            }

        } while (true);

        return valueEnd;
    }

    /***
     * 针对不同敏感类别信息不同处理策略
     * @param subMsg
     * @param sensitiveKeysEnum
     * @return
     */
    private static String desensitize(String subMsg, SensitiveKeysEnum sensitiveKeysEnum) {
        if (StringUtils.isBlank(subMsg)) {
            return subMsg;
        }
        StringBuffer sb = new StringBuffer("");
        int subMsgLength = subMsg.length();
        switch (sensitiveKeysEnum) {
            //李四 显示为 *四
            case NAME:
                sb.append(getFilterLength(subMsgLength - 1));
                sb.append(subMsg.substring(subMsgLength - 1));
                break;
            case EMAIL:
                //邮件显示  111*******************.com
                sb.append(subMsg.substring(0, 3));
                sb.append(getFilterLength(subMsgLength - 7));
                sb.append(subMsg.substring(subMsgLength - 4));
                break;
            case PHONE:
                //152****1234
                sb.append(subMsg.substring(0, 3));
                sb.append(getFilterLength(subMsgLength - 7));
                sb.append(subMsg.substring(subMsgLength - 4));
                break;
            case IDCARD:
                //身份证保持前三位明文
                //123**************
                sb.append(subMsg.substring(0, 3));
                sb.append(getFilterLength(subMsgLength - 3));
                break;
            case ADDRESS:
                //地址保留前六位明文 江苏省南京市*********
                if (subMsgLength > 6) {
                    sb.append(subMsg.substring(0, 6));
                    sb.append(getFilterLength(subMsgLength - 6));
                } else {
                    sb.append(subMsg);
                }
                break;
            case BANKCARD:
                //银行卡保持最后四位明文
                sb.append(getFilterLength(subMsgLength - 4));
                sb.append(subMsg.substring(subMsgLength - 4));
                break;
            case IMAGEURL:
                //图片地址服务器地址全部屏蔽
                sb.append(getFilterLength(subMsgLength));
                break;
            case PASSWORD:
                sb.append(getFilterLength(subMsgLength));
                break;
            default:
                break;
        }
        return sb.toString();
    }

    /***
     * 返回***字符串
     * @param toBeFilterLength
     * @return
     */
    private static String getFilterLength(int toBeFilterLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < toBeFilterLength; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    /****
     * 脱敏1.0 方法
     * @param submsg
     * @return
     */
    private static String tuomin(String submsg) {

        StringBuffer sbResult = new StringBuffer();
        if (submsg != null && submsg.length() > 0) {
            int len = submsg.length();
            if (len > 8) { //8位以上的    隐掉中间4位
                for (int i = len - 1; i >= 0; i--) {
                    if (len - i < 5 || len - i > 8) {
                        sbResult.insert(0, submsg.charAt(i));
                    } else {
                        sbResult.insert(0, '*');
                    }
                }
            } else { //8位以下的全部使用 *
                for (int i = 0; i < len; i++) {
                    sbResult.append('*');
                }
            }
        }
        return sbResult.toString();
    }
}
