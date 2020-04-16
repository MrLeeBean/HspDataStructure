package com.shuai;


public class Test {

    public static void main(String[] args) {

        System.out.println(method("dukailabcab"));
    }

    public static String method(String str) {
        String strRes = "";
        int k = 1;
        for (int i = 0; i < str.length(); i += k) {
            if ("a".equals(String.valueOf(str.charAt(i))) && "b".equals(String.valueOf(str.charAt(i + 1)))) {
                k = 2;
                continue;
            }
            k = 1;
            strRes += String.valueOf(str.charAt(i));
        }
        return strRes;
    }
}



