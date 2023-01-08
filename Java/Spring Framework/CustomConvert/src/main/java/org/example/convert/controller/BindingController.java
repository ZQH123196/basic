package org.example.convert.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("binding")
public class BindingController {

    /**
     * http://localhost:8080/binding/getLocalDateTime/2023-01-04 06:07:59
     * 2023-01-04 06:09:26.940  WARN 16892 --- [nio-8080-exec-3] .w.s.m.s.DefaultHandlerExceptionResolver :
     *  Resolved [org.springframework.web.method.annotation.MethodArgumentTypeMismatchException:
     *      Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDateTime';
     *          nested exception is org.springframework.core.convert.ConversionFailedException:
     *              Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.PathVariable java.time.LocalDateTime] for value '2023-01-04 06:07:59'; nested exception is java.lang.IllegalArgumentException: Parse attempt failed for value [2023-01-04 06:07:59]]
     *
     * 默认并不支持这种转换，需要自定义转换器
     *  @param date
     * @return
     */
    @RequestMapping("getLocalDateTime/{date}")
    public String getLocalDateTime(@PathVariable LocalDateTime date) {
        return date.format(DateTimeFormatter.ISO_DATE_TIME);
    }


}
