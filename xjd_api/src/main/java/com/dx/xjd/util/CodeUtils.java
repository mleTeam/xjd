package com.dx.xjd.util;

public class CodeUtils {

    public static String getUrlByCode(String code) {
        char[] charArray = code.toCharArray();
        // A-Z 对应数字65-90 a-z 对应数字97-122
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '_') {
                // 字符在97-122之间的都是小写字母，在原基础上加32转换成大写
                if (charArray[i + 1] >= 97 && charArray[i + 1] <= 122) {
                    int upper = charArray[i + 1] - 32;
                    charArray[i + 1] = (char) upper;
                }
            }
        }
        StringBuffer url = new StringBuffer("");
        for (int i = 0; i < charArray.length; i++) {
            url.append(charArray[i]);
        }
        return url.toString().replace("_", "");
    }

}
