package top.okeng.rpc.template;


import top.okeng.rpc.response.BaseResult;

import java.util.Objects;
import java.util.StringJoiner;


public class AbstractProviderTemplate {

    protected <T extends BaseResult> T msgHandle(T baseResult) {
        baseResult.setMessage(baseResult.getMessage());
        return baseResult;
    }

    protected static Integer formatRet(String code) {
        if (Objects.isNull(code)) {
            return -1;
        }
        return Integer.valueOf(code);
    }

    protected static Integer formatRet(Integer featureCode, String code) {
        if (Objects.isNull(code)) {
            return -1;
        }
        final int codeLength = code.length();
        return Integer.valueOf(new StringJoiner("").add(String.format("%01d", featureCode)).add(addZeroForLeft(code, 4)).toString());

    }

    /**
     * @param str          原始字符串
     * @param formatLength 指定要格式化的长度
     * @return 补0后的字符串
     * @描述: 字符串前面补0
     */
    private static String addZeroForLeft(String str, int formatLength) {
        int strLength = str.length();
        if (formatLength > strLength) {
            // 计算实际需要补0长度
            formatLength -= strLength;
            // 补0操作
            str = String.format("%0" + formatLength + "d", 0) + str;
        }
        return str;
    }
}
