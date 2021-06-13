package com.sxw;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

public class WebClientTest {

    @Test
    public void test() {
        WebClient webClient = WebClient.create();
        Mono<String> mono = webClient.get().uri("http://180.101.49.11").retrieve().bodyToMono(String.class);



        System.out.println(mono.block());
    }

    //创建webClient
    private WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8888/")
            .build();

    @Test
    void testUpload()  {
        // 待上传的文件（存在客户端本地磁盘）
        String filePath = "D:\\data\\local\\splash.png";
        // 封装请求参数
        FileSystemResource resource = new FileSystemResource(new File(filePath));
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("uploadFile", resource);  //服务端MultipartFile uploadFile
        //param.add("param1", "test");   //服务端如果接受额外参数，可以传递

        // 发送请求
        Mono<String> mono = webClient
                .post() // POST 请求
                .uri("/upload")  // 请求路径
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(BodyInserters.fromMultipartData(param))
                .retrieve() // 获取响应体
                .bodyToMono(String.class); //响应数据类型转换

        // 输出结果
        System.out.println(mono.block());
    }

}
