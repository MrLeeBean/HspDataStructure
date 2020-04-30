package com.shuai;


public class Test {

    public static void main(String[] args) {
        byte b = 21;
        String method = method(b, false);
        System.out.println(method);

    }


    public static String method(byte b, boolean flag) {
        if (flag) {
            return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        } else {
            return String.format("%s", Integer.toBinaryString(b));
        }

    }
}



