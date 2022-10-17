package com.example.validation.vo.validator;

import com.example.validation.vo.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        log.info(Person.class.toString());
        log.info(clazz.toString());
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 注意第二个入参 errorCode 最终会根据这个 errorCode 去绑定到其他详细的错误信息，相当于 id 的作用。
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        Person person = (Person) target;
        if (person.getAge() < 0) {
            errors.rejectValue("age", "negativevalue");
        } else if (person.getAge() > 120) {
            errors.rejectValue("age", "too.darn.old");
        }
    }
}
