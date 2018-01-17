package com.taylor.reputation.seckill.util;

import java.util.Objects;

/**
 * @taylor
 */
public final class StringUtil {

    private StringUtil() {
    }

    public static void requireNonNullAndLength(String str) {
        if (Objects.requireNonNull(str).length() <= 0) {
            throw new NullPointerException();
        }
    }
}
