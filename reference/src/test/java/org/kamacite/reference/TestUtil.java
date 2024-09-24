/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.reference;

public class TestUtil {

    public static void string2decimal(char[] out, int outLen, String s) {
        for (int i = 0; i < outLen; i++) {
            out[i] = s.charAt(i);
        }
    }

    public static void assert_string_equals(char[] a, char[] b, int len) {
        for (int i = 0; i < len; i++) {
            assert a[i] == b[i];
        }
    }

    public static void assert_int_equals(int a, int b) {
        assert a == b;
    }
}
