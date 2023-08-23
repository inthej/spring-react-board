package com.board.backend.common.utils;

import com.board.backend.exception.ValidationNullException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationUtil {
    public static <T> T validateNonNull(T obj, String message) {
        if (obj == null)
            throw new ValidationNullException(message);
        return obj;
    }
}
