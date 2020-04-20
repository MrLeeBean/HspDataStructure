package com.shuai;


public class Test {

    public static void main(String[] args) {
        String str = "https://dl4.weshineapp.com/kk/phrase/icon/20200403/b83225967fb3c8eff2208e7da2917506.jpg";
        System.out.println(me(str));
    }

    public static String me(String str) {
        String key = "kk/phrase/icon";
        return str.substring(str.indexOf(key));
    }


}



