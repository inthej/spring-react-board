package com.board.backend.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectUtil {
    public static boolean empty(Object obj) {
        return obj == null;
    }

    public static boolean nonEmpty(Object obj) {
        return !ObjectUtil.empty(obj);
    }
}
