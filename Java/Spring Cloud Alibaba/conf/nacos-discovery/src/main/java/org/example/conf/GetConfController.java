package org.example.conf;

import org.example.conf.bean.GlobalConf;
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



    @RequestMapping("getGlobalConf")
    public String getGlobalConf() {
        return globalConf.toString();
    }
}
