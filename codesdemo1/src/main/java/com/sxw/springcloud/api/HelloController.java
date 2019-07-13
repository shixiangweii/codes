package com.sxw.springcloud.api;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Description:
 * User: shixiangweii
 * Date: 2019-05-03
 * Time: 18:22
 *
 * @author shixiangweii
 */
@RestController
public class HelloController {
    private final Logger logger = Logger.getLogger(getClass());

    /**
     * 从注册中心的角度来说，/hello服务是注册中心的客户端，eureka-client
     */
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "Hello World";
    }
}
