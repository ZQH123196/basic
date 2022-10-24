package com.example.validation.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

/**
 * ConstraintValidator<生效的注解, 生效的目标类型>
 * @author ZQH12
 */
public class UserStatusValidator implements ConstraintValidator<UserStatus, Integer> {
    @Override
    public void initialize(UserStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if ( value == null ) {
            return false;
        }
        Set<Integer> set = new HashSet(){{
            add(1000);
            add(1001);
            add(1002);
        }};

        return set.contains(value);
    }
}
