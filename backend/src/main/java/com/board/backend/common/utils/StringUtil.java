package com.board.backend.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {
    private static final String EMPTY = "";

    public static boolean empty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean nonEmpty(String str) {
        return !StringUtil.empty(str);
    }

    public static String nvl(String str) {
        return StringUtil.nvl(str, EMPTY);
    }

    public static String nvl(String str, String defaultValue) {
        if (StringUtil.empty(str)) {
            return defaultValue;
        }
        return str;
    }

    public static String nvl2(String str, String str1, String str2) {
        if (StringUtil.empty(str)) {
            return str2;
        }
        return str1;
    }
}
