/*
 * Copyright (c) 2024, Alden Torres
 *
 * Licensed under the terms of the MIT license.
 * Copy of the license at https://opensource.org/licenses/MIT
 */

package org.kamacite.jvm;



public class Util {

    public static void decimal2bin(byte[] out, int outLen, char[] str, int strLen) {
        for (int j = 0; j < strLen; j++) {
            char c = str[j];
            int digit = c - '0';
            int carry = 0;
            for (int i = 0; i < outLen; i++) {
                int temp = (out[i] & 0xFF) * 10 + carry;
                out[i] = (byte) temp;
                carry = (temp >>> 8) & 0xFF;
            }
    
            carry = digit;
            for (int i = 0; i < outLen; i++) {
                int temp = (out[i] & 0xFF) + carry;
                out[i] = (byte) temp;
                carry = (temp >>> 8) & 0xFF;
            }
    
        }
    
    }
    
    public static void bin2decimal(char[] out, int outLen, byte[] n, int nLen) {
        for (int i = 0; i < outLen; i++) {
            out[i] = '0';
        }
    
        for (int i = nLen - 1; i >= 0; i--) {
            int carry = n[i] & 0xFF;
            for (int j = outLen - 1; j >= 0; j--) {
                int digit = out[j] - '0';
                int temp = digit * 256 + carry;
                out[j] = (char) ('0' + (temp % 10));
                carry = temp / 10;
            }
    
        }
    
    }
    
    public static int compare(byte[] a, byte[] b, int len) {
        int gt = 0;
        int eq = 1;
        int x1 = 0;
        int x2 = 0;
        for (int i = len - 1; i >= 0; i--) {
            x1 = a[i] & 0xFF;
            x2 = b[i] & 0xFF;
            gt = gt | (((x2 - x1) >> 8) & eq);
            eq = eq & (((x2 ^ x1) - 1) >> 8);
        }
    
        return (gt + gt + eq) - 1;
    }
    
    public static void add(byte[] a, byte[] b, int len) {
        int c = 0;
        for (int i = 0; i < len; i++) {
            c = c + (a[i] & 0xFF) + (b[i] & 0xFF);
            a[i] = (byte) (c & 0xFF);
            c = c >>> 8;
        }
    
    }
    
}
