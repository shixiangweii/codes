package com.sxw.code.acm.dp.maxsum_1003;

import java.util.Scanner;

/**
 * 第一步：设计，思考，规划(审题，对问题理解，概念使用，经验)
 * <p>
 * S1，审题，观察问题
 * 问题规模，
 * A(n)表示，长度为n的整型数组的，最大子序列
 * A(n+1)，长度为n+1的数组，最大子序列
 * 那么其实就是增加一个arr[n+1]，对之前A(n)结果的影响，
 * <p>
 * 分析
 * 那么可能，
 * A(n+1):
 * arr[n+1];
 * arr[n+1]+A(n)，因为“连续”其实就是：arr[n+1],arr[n].....，A(n)最后包含了arr[n]
 * A(n)，arr[n]，不在A(n)结果中
 * <p>
 * 3种的最大值，
 * <p>
 * 并且其实可以进一步发现，其实A(n)，最终结果序列，必然值以一个arr[i]结尾了，
 * 那A(n) = A(i)，其实就是之前的结果
 * <p>
 * 其实 A(n) = {s(1),s(2)...s(i)..s(n)}max
 * s(n)，以arr[n]结尾，包含arr[n]的最大子序列
 * <p>
 * 那么从n=1去递推，
 * <p>
 * <p>
 * Description: 最大连续子序列求和
 * User: shixiangweii
 * Date: 2019-01-15
 * Time: 19:56
 *
 * @author shixiangweii
 */
public class Main {
    /**
     * 第二步：具体编码实现（用具体某一种语言去具体实现，涉及一些具体的语法的技巧，函数，方法的使用）
     * 比如更好的优化，可以不用s[]数组吗，直接用一个int直接保存就可以，这就是编码级别的优化
     * 具体一种思想，用编码表现出来的时候，这时候也是有编码的技巧的
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int line = in.nextInt();
        int cnt = 1;
        while (line-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int max = a[0], start = 0, end = 0, i, sb = 0;
            // 可以省去一个数组
            int[] s = new int[n];
            s[0] = a[0];
            for (i = 1; i < n; i++) {
                // s[i - 1] + a[i] >= a[i]
                if (s[i - 1] >= 0) {
                    s[i] = s[i - 1] + a[i];
                } else {
                    s[i] = a[i];
                    // 这个坐标自己还是理解不到位，还是不对
                    sb = i;
                }
                if (s[i] > max) {
                    max = s[i];
                    end = i;
                    start = sb;
                }
            }
            System.out.println("Case " + cnt + ":");
            cnt++;
            System.out.println(max + " " + (start + 1) + " " + (end + 1));
            if (line != 0) {
                System.out.println();
            }
        }
        in.close();
    }
}
