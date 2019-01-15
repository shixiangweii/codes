package com.sxw.code.acm.dp.maxsum_1003;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int c = 1;
        while (l-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int max = a[0], start = 0, end = 0, i, sb = 0, su = max;
            for (i = 1; i < n; i++) {
                if (su >= 0) {
                    su += a[i];
                } else {
                    su = a[i];
                    sb = i;
                }
                if (su > max) {
                    max = su;
                    end = i;
                    start = sb;
                }
            }
            System.out.println("Case " + c + ":");
            c++;
            System.out.println(max + " " + (start + 1) + " " + (end + 1));
            if (l != 0) {
                System.out.println();
            }
        }
        in.close();
    }
}
