/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.reference;

import org.junit.jupiter.api.Test;

import static org.kamacite.reference.TestUtil.assert_int_equals;
import static org.kamacite.reference.TestUtil.assert_string_equals;
import static org.kamacite.reference.Util.*;

public class UtilTest {

    @Test
    public void test_parse_decimal() {
        byte[] n = new byte[64];
        int nLen = 64;

        char[] strIn = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int strInLen = 9;

        decimal2bin(n, nLen, strIn, strInLen);

        char[] strOut = new char[9];
        int strOutLen = 9;

        bin2decimal(strOut, strOutLen, n, nLen);

        assert_int_equals(strInLen, strOutLen);
        assert_string_equals(strIn, strOut, strInLen);
    }

    @Test
    public void test_compare() {
        byte[] n1 = new byte[64];
        int n1Len = 64;
        byte[] n2 = new byte[64];
        int n2Len = 64;


        char[] strIn = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int strInLen = 9;

        decimal2bin(n1, n1Len, strIn, strInLen);
        strIn[0] = '2';
        decimal2bin(n2, n2Len, strIn, strInLen);

        assert_int_equals(n1Len, n2Len);

        int r1 = compare(n1, n2, n1Len);
        assert_int_equals(r1, -1);

        int r2 = compare(n1, n1, n1Len);
        assert_int_equals(r2, 0);

        int r3 = compare(n2, n1, n1Len);
        assert_int_equals(r3, 1);
    }

    @Test
    public void test_double_by_adding() {
        byte[] n = new byte[64];
        int nLen = 64;

        char[] strIn = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        int strInLen = 9;

        decimal2bin(n, nLen, strIn, strInLen);

        add(n, n, nLen);

        char[] strOut = new char[9];
        int strOutLen = 9;

        bin2decimal(strOut, strOutLen, n, nLen);

        // 246913578
        char[] strExpected = {'2', '4', '6', '9', '1', '3', '5', '7', '8'};
        int strExpectedLen = 9;

        assert_int_equals(strExpectedLen, strOutLen);
        assert_string_equals(strExpected, strOut, strInLen);
    }
}
