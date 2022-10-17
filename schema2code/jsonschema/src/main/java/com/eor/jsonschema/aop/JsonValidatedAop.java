package com.eor.jsonschema.aop;



import com.eor.jsonschema.annontation.SchemaValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;
import java.io.InputStream;

@Component
@Aspect
@Slf4j
public class JsonValidatedAop {

    @Pointcut("@annotation(com.eor.jsonschema.annontation.SchemaValidator)")
    private void pointcut() {}

    @Before("pointcut() && @annotation(schemaValidator)")
    public void before(JoinPoint joinPoint, SchemaValidator schemaValidator) {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            String jsonString = com.alibaba.fastjson.JSONObject.toJSONString(args[0]);
            String validMsg;
            try {
                validMsg = validJson(jsonString, schemaValidator.schemaName());
            } catch (RuntimeException e) {

            }

        }
        log.info("数据：" + com.alibaba.fastjson.JSONObject.toJSONString(args) + schemaValidator.schemaName());

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
            JSONObject jsonData = new JSONObject(jsonString);
            InputStream in = getClass().getResourceAsStream("/schemas/" + schemaName);

            JSONObject schemaData = new JSONObject(new JSONTokener(in));
            Schema schema = SchemaLoader.load(schemaData); // 一般使用缓存
            schema.validate(jsonData);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            log.error(e.getViolatedSchema().getDescription()); // 拿到原始 schema 的描述
            sBuilder.append(e.getMessage());
            sBuilder.append(e.getViolatedSchema().getDescription());
            throw e; // 继续往上抛
        }
        return sBuilder.toString();
    }

}

