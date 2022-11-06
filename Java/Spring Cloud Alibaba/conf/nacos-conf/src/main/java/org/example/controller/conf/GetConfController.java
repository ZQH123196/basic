package org.example.controller.conf;

import org.example.controller.conf.bean.GlobalConf;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("conf")
@RefreshScope
public class GetConfController {


    @Resource
    GlobalConf globalConf;

// TODO 添加 @RefreshScope 之后复杂对象的返回有问题
//    @RequestMapping("getGlobalConf")
//    public GlobalConf getGlobalConf() {
//        return globalConf;
//    }

    @RequestMapping("getGlobalConf")
    public String getGlobalConf() {
        return globalConf.toString();
    }
}
