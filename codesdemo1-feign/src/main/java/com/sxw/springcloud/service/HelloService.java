package com.sxw.springcloud.service;

import com.sxw.springcloud.domain.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author shixi
 * 服务名不区分大小写，HELLO-SERVICE，这种大写也是可以的
 * 其实有点类似dubbo，消费者依赖api模块，直接注入接口
 *
 * Feign中参数必须通过value属性指明具体的参数名，否则会异常
 */
@FeignClient("hello-service")
public interface HelloService {
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    String hello(@RequestBody User user);

}
