package com.sxw.code.concurrent.forkjoin;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-04-04
 * Time: 21:33
 */
public class BinarySum {

    /**
     * 左闭又开
     * [start,end)
     *
     * @param arr
     * @param startIdx
     * @param endIdx
     * @return
     */
    public static int bSum(int[] arr, int startIdx, int endIdx) {
        int len = endIdx - startIdx;
        if (len <= 0) {
            return 0;
        }
        if (len == 1) {
            return arr[startIdx];
        }
        if (len == 2) {
            return arr[startIdx] + arr[endIdx - 1];
        }
        // 这里计算的是坐标
        int midIdx = startIdx + len / 2;
        int sumLeft = bSum(arr, startIdx, midIdx);
        int sumRight = bSum(arr, midIdx, endIdx);
        return sumLeft + sumRight;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1};
        int sum = bSum(a, 0, a.length);
        System.out.println(sum);
    }

}
