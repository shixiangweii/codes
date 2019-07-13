package com.sxw.springcloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 * @author shixiangweii
 *
 * java -jar eureka-server.jar --spring.profiles.active=peer1
 * java -jar eureka-server.jar --spring.profiles.active=peer2
 */
@EnableEurekaServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder((App.class)).web(true).run(args);
    }
}
