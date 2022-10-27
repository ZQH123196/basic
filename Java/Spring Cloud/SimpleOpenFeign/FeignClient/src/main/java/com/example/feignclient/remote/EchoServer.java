package com.example.feignclient.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 使用 url 一般用于测试，不要跟服务中的名字相同
//@FeignClient(value = "11", url = "http://localhost:9000/EchoServer/hello")
// 去配置文件中匹配 EchoServerName 名称，然后取其 URI 在拼接上 path，然后在拼接内部 @RequestMapping 的路径
@FeignClient(value = "EchoServerName", path = "EchoServer")
public interface EchoServer {
    @RequestMapping("hello")
    public String hello();

    @RequestMapping("echo")
    public String echo(@RequestParam String str);
}
