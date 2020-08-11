package com.shuai._algorithm.kmp;

import java.util.Arrays;

/**
 * KMP算法
 */
public class KMPTest {

    public static void main(String[] args) {

        String sStr = "BBC ABCDAB ABCDABCDABDE";
        String pStr = "ABCDABD";

        //匹配表
        int[] nextArr = getNextArrOpt(pStr);
        //查找目标字符串的匹配位置
        int i = kmpSearch(sStr, pStr, nextArr);

        System.out.println("Next数组为：" + Arrays.toString(nextArr) + "\n" + "匹配到的位置为：" + i);

    }


    /**
     * 获取匹配表next数组
     *
     * @param pStr 模式串
     * @return
     */
    public static int[] getNextArr(String pStr) {

        int len = pStr.length();
        char[] p = pStr.toCharArray();

        int[] next = new int[len];
        next[0] = -1;

        int j = 0;
        int k = -1;

        while (j < len - 1) {
            // p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[j] == p[k]) {
                j++;
                k++;
                next[j] = k;//相当于若p[k] == p[j]，则next[j + 1] = next [j] + 1 = k + 1；
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 获取匹配表next数组 优化版
     *
     * @param pStr 模式串
     * @return
     */
    public static int[] getNextArrOpt(String pStr) {

        int len = pStr.length();
        char[] p = pStr.toCharArray();

        int[] next = new int[len];
        next[0] = -1;

        int j = 0;
        int k = -1;

        while (j < len - 1) {
            // p[k]表示前缀，p[j]表示后缀
            if (k == -1 || p[j] == p[k]) {
                j++;
                k++;
                // 较之前next数组求法，改动在如下代码
                if (p[j] != p[k]) {//相当于p[j]!=p[next[j]]
                    next[j] = k;
                } else {
                    // 因为不能出现p[j] = p[ next[j ] ]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[k];
                }

            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * kmp算法
     *
     * @param sStr 文本串
     * @param pStr 模式串
     * @param next 匹配表
     * @return
     */
    public static int kmpSearch(String sStr, String pStr, int[] next) {

        int i = 0;
        int j = 0;

        char[] s = sStr.toCharArray();
        char[] p = pStr.toCharArray();

        int sLen = s.length;
        int pLen = p.length;

        while (i < sLen && j < pLen) {
            // ①如果 j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s[i] == p[j]) {
                i++;
                j++;
            }
            // ②如果 j!= -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
            // next[j]即为j所对应的next值
            else {
                j = next[j];
            }
        }

        if (j == pLen) {
            return i - j;
        } else {
            return -1;
        }
    }
}
