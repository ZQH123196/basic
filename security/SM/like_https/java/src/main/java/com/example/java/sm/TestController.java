package com.example.java.sm;


import cn.hutool.core.util.HexUtil;
import com.example.java.sm.utils.MySmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {


    @RequestMapping("helloWorld")
    public String helloWorld() {
        return "helloWorld";
    }

    @RequestMapping("pageCloseEvent")
    public void pageCloseEvent() {
        log.info("invoke pageCloseEvent!");
    }


    @PostMapping("sm2Des")
    public void sm2Des(@RequestHeader("EncSm4Key") String EncSm4Key) {

        String sm4Key = MySmUtil.sm2Decrypt(EncSm4Key);
        log.info("invoke sm2Des!sm4=[{}]", sm4Key);
    }

    @PostMapping("registerSm4Key")
    public void registerSm4Key(@RequestHeader("TableName") String TableName,
                               @RequestHeader("EncSm4Key") String EncSm4Key) {
        log.info("into registerSm4Key!");
        log.info("sm4KeyMap={}", MySmUtil.sm4KeyMap);
        String sm4KeyHex = MySmUtil.sm2Decrypt(EncSm4Key);
        MySmUtil.sm4KeyMap.put(TableName, HexUtil.decodeHex(sm4KeyHex));
    }


    @PostMapping("sm4DecryptTest")
    public void sm4DecryptTest(@RequestHeader("TableName") String TableName,
                               @RequestHeader("EncSm4Data") String EncSm4Data) {
        log.info("into sm4DecryptTest!");
        log.info("sm4KeyMap={}", MySmUtil.sm4KeyMap);
        byte[] sm4Key = MySmUtil.sm4KeyMap.get(TableName);
        String decSm4Data = MySmUtil.sm4Decrypt(EncSm4Data, sm4Key);
        log.info("decSm4Data = [{}]", decSm4Data);
    }


}
