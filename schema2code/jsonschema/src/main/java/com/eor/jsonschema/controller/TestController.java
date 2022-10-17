package com.eor.jsonschema.controller;

import com.eor.jsonschema.annontation.SchemaValidator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
@Slf4j
public class TestController {

    @CrossOrigin
    @PostMapping("/echo")
    @SchemaValidator(schemaName = "userSchema.json")
    public Map<String, Object> echo(@RequestBody Map<String, Object> reqMap) {
        reqMap.put("resCode", "0000");
        return reqMap;
    }

}
