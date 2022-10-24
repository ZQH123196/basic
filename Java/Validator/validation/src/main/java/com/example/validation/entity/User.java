package com.example.validation.entity;


import com.example.validation.customvalidation.UserStatus;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public class User {

    /**
     * invalid: null, -1~-∞
     */
    @NotNull
    @PositiveOrZero
    private long uid;

    /**
     * invalid: null, "", "   "
     */
    @NotBlank
    private String name;

    /**
     * invalid: null, -1 ~-∞, 131~+∞
     * @Size(min = 0, max = 130)
     */
    @NotNull
    @Min(value = 0, message = "年龄不能小于 {value},当前年龄 ${validatedValue}.") @Max(130)
    private Integer age;

    @UserStatus
    private Integer userSatus;

    public Integer getUserSatus() {
        return userSatus;
    }

    public void setUserSatus(Integer userSatus) {
        this.userSatus = userSatus;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
