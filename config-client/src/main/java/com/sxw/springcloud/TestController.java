package com.sxw.springcloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shixiangweii
 * @date 2019/7/21 13:28
 */
@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;

    @RequestMapping("/from")
    public String getFrom() {
        return this.from;
    }
}
