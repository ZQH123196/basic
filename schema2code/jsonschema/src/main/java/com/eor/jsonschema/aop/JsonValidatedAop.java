package com.eor.jsonschema.aop;


import com.alibaba.fastjson.JSONObject;


import com.eor.jsonschema.annontation.SchemaValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
@Aspect
@Slf4j
public class JsonValidatedAop {

    @Pointcut("@annotation(com.eor.jsonschema.annontation.SchemaValidator)")
    private void pointcut() {}

    @Before("pointcut() && @annotation(SchemaValidator)")
    public void before(JoinPoint joinPoint, SchemaValidator jsonValid) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            String jsonString = JSONObject.toJSONString(args[0]);
            /** 验证的结果，可以返回，也可以写个全局捕捉的异常 */
            String validMsg = validJson(jsonString, jsonValid.schemaName());
        }
        log.info("数据：" + JSONObject.toJSONString(args) + jsonValid.schemaName());

    }


    /**
     *
     * @param jsonString 待校验 json
     * @param schemaName 用于校验的 schema 名称
     * @return 返回失败消息，为空则校验成功
     */

    public String validJson(String jsonString, String schemaName) {

        StringBuilder sBuilder = new StringBuilder();
        try {
            //InputStream inputStream = getClass().getResourceAsStream("/schema/hello.json");
            //org.json.JSONObject rawSchema = new org.json.JSONObject(new JSONTokener(inputStream));

            org.json.JSONObject rawSchema = new org.json.JSONObject(jsonString);
            InputStream in1 = getClass().getResourceAsStream("/schemas/" + schemaName);
            org.json.JSONObject sSchema = new org.json.JSONObject(new JSONTokener(in1));
            Schema schema = SchemaLoader.load(sSchema);
            schema.validate(rawSchema);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            sBuilder.append(e.getMessage());
            throw e; // 继续往上抛
        }
        return sBuilder.toString();
    }

}

