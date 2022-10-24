package com.example.validation.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidationHelper {

    // thread safety
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> List<String> validate(T targetBean) {
        Set<ConstraintViolation<T>> set = validator.validate(targetBean);
        List<String> violationList = set.stream().map(v -> "property:" + v.getPropertyPath() + ",value:[" + v.getInvalidValue() + "],invalid msg:" + v.getMessage()).collect(Collectors.toList());
        return violationList;
    }


    public static <T> List<String> validateByGroup(T targetBean, Class<?>... groups) {
        Set<ConstraintViolation<T>> set = validator.validate(targetBean, groups);
        List<String> violationList = set.stream().map(v -> "property:" + v.getPropertyPath() + ",value:[" + v.getInvalidValue() + "],invalid msg:" + v.getMessage()).collect(Collectors.toList());
        return violationList;
    }
}
