package com.example.webfluxsimple.action.action;

import com.example.webfluxsimple.action.handler.MessageHandle;
import com.example.webfluxsimple.action.pojo.HelloVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping("/action")
public class HelloAction {

    @Resource
    MessageHandle messageHandle;

    @RequestMapping("/hello")
    public Mono<HelloVo> hello() {
        return messageHandle.hello();
    }
}
