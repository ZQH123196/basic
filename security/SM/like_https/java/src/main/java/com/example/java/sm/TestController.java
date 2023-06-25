package com.example.java.sm;


import cn.hutool.core.util.HexUtil;
import com.example.java.sm.utils.MySmUtil;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.Charset;
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


    /**
     * 文件名 org.apache.tomcat.util.http.fileupload.FileUploadBase#getFileName(org.apache.tomcat.util.http.fileupload.FileItemHeaders)
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/uploadSm4")
    public boolean uploadSm4(MultipartFile file,
                             @RequestHeader("sm4KeyHex") String sm4KeyHex) throws IOException {
        byte[] sm4Key = Hex.decode(sm4KeyHex);
        String encHexStr = new String(file.getBytes());
        String decHexStr = MySmUtil.sm4Decrypt(encHexStr, sm4Key);
        String realFileContent = HexUtil.decodeHexStr(decHexStr, Charset.defaultCharset());
        log.info("realFileContent = [{}]", realFileContent);
        return true;
    }

}
