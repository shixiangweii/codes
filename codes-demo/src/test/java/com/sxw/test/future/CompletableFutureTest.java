package com.sxw.test.future;

import java.util.concurrent.CompletableFuture;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-20
 * Time: 11:29
 *
 * @author shixiangweii
 */
public class CompletableFutureTest {

    public static String getDataForF1(String url, String param) {
        long s = System.currentTimeMillis();
        System.out.println("f1-" + param);
        try {
            Thread.sleep(20L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "res1-forf1" + param;
    }

    public static String getData1(String url, String param) {
        long s = System.currentTimeMillis();
        try {
            Thread.sleep(29L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getData1-" + Thread.currentThread().getName() + "-" + param);
        return "res1-" + param;
    }

    public static String getData2(String param) {
        long s = System.currentTimeMillis();
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("getData2-" + Thread.currentThread().getName());
        return "res2-" + param;
    }

    public static void main(String[] args) throws Exception {

        String url = "f1";
        String param = "f1";

        String url3 = "f3";
        String param3 = "f3";


        CompletableFuture<String> future1 = new CompletableFuture<>();
        new Thread(() -> future1.complete(getDataForF1(url, param)), "f1").start();

        CompletableFuture<String> future2 = future1.thenApplyAsync(r -> {
            String str = "";
            return getData2(r + str);
        });

        CompletableFuture<String> future3 = new CompletableFuture<>();
        new Thread(() -> future3.complete(getData1(url3, param3)), "f3").start();

        CompletableFuture<String> resFuture = future2.thenCombineAsync(future3, (r2, r3) -> {
            String data = getData1("final", "final");
            return "final res:" + data + r2 + r3;
        }).exceptionally(e -> "exception");


        String x = resFuture.get();
        System.out.println("------------------------\n" + x);
    }
}
