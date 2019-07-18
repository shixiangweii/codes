package com.sxw.springcloud.service;


import com.shixiangweii.cloud.service.HelloService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends HelloService {

}
