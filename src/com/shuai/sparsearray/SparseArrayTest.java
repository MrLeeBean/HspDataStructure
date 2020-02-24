package com.shuai.sparsearray;

/**
 * 稀疏数组
 */
public class SparseArrayTest {
    public static void main(String[] args) {

        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 12;
        chessArr[2][3] = 34;

        System.out.println("-------------原始数组-------------");
        printArray(chessArr);
        System.out.println("-------------原始数组转稀疏数组-------------");
        printArray(switchChessArrToSparseArr(chessArr));
        System.out.println("-------------稀疏数组转原始数组-------------");
        printArray(switchSparseArrToChessArr(switchChessArrToSparseArr(chessArr)));

    }

    /**
     * 打印一个二维数组
     *
     * @param arr
     */
    public static void printArray(int[][] arr) {
        if (arr != null) {
            for (int[] row : arr) {
                for (int item : row) {
                    System.out.printf("%d\t", item);
                }
                System.out.println();
            }
        }
    }

    /**
     * 原始数组转换为稀疏数组
     *
     * @param chessArr
     * @return
     */
    public static int[][] switchChessArrToSparseArr(int[][] chessArr) {

        if (chessArr == null) {
            throw new RuntimeException("输入数组不能为NULL");
        }

        if (chessArr.length <= 0 || chessArr[0].length <= 0) {
            throw new RuntimeException("输入数组长度不能为0");
        }

        int sum = 0;
        for (int[] item1 : chessArr) {
            for (int item2 : item1) {
                if (item2 != 0) {
                    sum++;
                }
            }
        }

        int[][] sparseArr = new int[sum + 1][3];
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        int count = 0;

        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }

            }
        }

        return sparseArr;
    }

    /**
     * 稀疏数组转换为原始数组
     *
     * @param sparseArr
     * @return
     */
    public static int[][] switchSparseArrToChessArr(int[][] sparseArr) {
        if (sparseArr == null) {
            throw new RuntimeException("输入数组不能为NULL");
        }

        if (sparseArr.length <= 0 || sparseArr[0].length <= 0) {
            throw new RuntimeException("输入数组长度不能为0");
        }

        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr;
    }
}
