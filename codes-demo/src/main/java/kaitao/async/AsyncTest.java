package kaitao.async;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.protocol.BasicAsyncResponseConsumer;
import org.apache.http.nio.protocol.HttpAsyncRequestProducer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-10-19
 * Time: 18:24
 * 其实所有的语法，所有的套路都是那么回事
 * 关键是要理解，异步非阻塞，请求-监听；生产者-消费者
 * 其实感觉最关键的还是，监听的机制
 * ? 通配符类型
 * ? extends T 表示类型的上界，表示参数化类型的可能是T 或是 T的子类
 * ? super T  表示类型下界（Java Core中叫超类型限定），表示参数化类型是此类型的超类型（父类型），直至Object
 *
 * @author shixiangweii
 */
public class AsyncTest {
    public static HttpAsyncClient httpAsyncClient;

    public static CompletableFuture<String> getHttpData(String url) {
        CompletableFuture asyncFuture = new CompletableFuture();
        HttpAsyncRequestProducer producer = HttpAsyncMethods.create(new HttpPost(url));
        BasicAsyncResponseConsumer consumer = new BasicAsyncResponseConsumer();
        FutureCallback callback = new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse httpResponse) {
                asyncFuture.complete(httpResponse);
            }

            @Override
            public void failed(Exception e) {
                asyncFuture.completeExceptionally(e);
            }

            @Override
            public void cancelled() {
                asyncFuture.cancel(true);
            }
        };
        httpAsyncClient.execute(producer, consumer, callback);
        return asyncFuture;
    }

    /**
     * f1 --|
     * f2 --|--merge
     * f3 --|
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        CompletableFuture<String> future1 = getHttpData("http://www.jd.com");
        CompletableFuture<String> future2 = getHttpData("http://www.jd.com");
        CompletableFuture<String> future3 = getHttpData("http://www.jd.com");
        // 可以把3个异步结果编排??
        // 这里可以返回Integer而不用一定要返回String
        // 这里用了大量的“行为参数化”，再加上lambda真的TMD很绕
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3);
        // ? super T，这里Void的父亲，就只有Object了
        // 把链式调用拆开来很多，lambda就可以理解了，只要关注行为，和参数推断
        CompletableFuture<Integer> integerCompletableFuture = voidCompletableFuture.thenApplyAsync((obj) -> 1);
        CompletableFuture<Integer> exceptionally = integerCompletableFuture.exceptionally(e -> 1);
        // 不阻塞主线程

        // 最终
        System.out.println(exceptionally.get());
    }

    public static void test2() throws Exception {
        CompletableFuture<String> future1 = getHttpData("http://www.jd.com");
        CompletableFuture<String> future2 = getHttpData("http://www.jd.com");
        // ? super ,所以这里是String, Object
        // 自己要开始习惯把返回类型，也当成一种参数
        BiConsumer<Object, String> stringStringBiConsumer = (future1Res, future2Res) -> {

        };
        CompletableFuture<Void> voidCompletableFuture = future1.thenAcceptBothAsync(future2, stringStringBiConsumer);

        // 最终
        System.out.println(voidCompletableFuture.get());
    }

    /**
     * 似乎可以理解什么叫做“编排”了
     * 就是几个future之间的依赖关系的处理
     *       -- f2 --
     * f1-->|       |-->result
     *      -- f3 --
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        CompletableFuture<String> future1 = getHttpData("http://www.jd.com");

        CompletableFuture<String> future2 = future1.thenApplyAsync((v) -> "result by service 2");

        CompletableFuture<String> future3 = getHttpData("http://www.jd.com");

        future2.thenCombineAsync(future3, (f2Res, f3Res) -> "").exceptionally(e -> "");
    }


    public static void testString(List<? super String> p, List<? extends String> p2) {

    }

    public static void testNumber(List<? super Integer> p, List<? extends Integer> p2) {

    }

    public static void testGen() {
        testString(new ArrayList<Object>(), new LinkedList<String>());

        testNumber(new ArrayList<Integer>(), new LinkedList<Integer>());
    }


}
