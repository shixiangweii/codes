package com.sxw.springcloud.api;

import com.shixiangweii.cloud.dto.User;
import com.shixiangweii.cloud.service.HelloService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shixiangweii
 * @date 2019/7/18 8:21
 * <p>
 * 其实这里的，"HelloService"就相当于给，之前的"Controller"定义一个接口，因为cloud基于http协议，所以是抽象抽象出来controller的接口
 * 而dubbo这种，就可以直接使用service的接口
 */
@RestController
public class RefactorHelloController implements HelloService {

    /**
     * 其实就是实现一个接口，按照契约中的接口签名，使得客户端可以依据接口绑定
     * controller的本质并没有变，所以这里的，虽然是实现，但是，@RequestParam,@RequestHeader,@RequestBody的注解不能少不然，
     * 取不到值，都是null
     */
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "Hello " + name;
    }

    @Override
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello(@RequestBody User user) {
        return "Hello " + user.getName() + ", " + user.getAge();
    }
}
