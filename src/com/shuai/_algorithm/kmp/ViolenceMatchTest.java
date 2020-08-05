package com.shuai._algorithm.kmp;

/**
 * 字符串暴力匹配
 */
public class ViolenceMatchTest {
    public static void main(String[] args) {
        String source = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        int index1 = match(source, "尚硅谷你尚硅你");
        int index2 = match(source, "尚硅谷你尚硅你~");
        System.out.println("index1=" + index1 + "\nindex2=" + index2);
    }

    /**
     * 暴力匹配算法
     * 判断 字符串source 中是否含有 字符串target
     *
     * @param source
     * @param target
     * @return 如果存在，就返回第一次出现的位置, 如果没有，则返回-1
     */
    public static int match(String source, String target) {

        char[] sourceChars = source.toCharArray();
        char[] targetChars = target.toCharArray();
        int sourceLen = sourceChars.length;
        int targetLen = targetChars.length;

        int i = 0; // i索引指向source
        int j = 0; // j索引指向target
        while (i < sourceLen && j < targetLen) {
            if (sourceChars[i] == targetChars[j]) {//匹配成功
                i++;
                j++;
            } else {//匹配失败
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == targetLen) {//已经完成匹配
            return i - j;
        } else {
            return -1;
        }
    }
}
