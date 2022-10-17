package com.example.validation.entity;

import jakarta.validation.constraints.NotNull;

/**
 * 用于测试级联校验 @valid
 * 比如这个 address 类，精度要求至少要填写省，而城市跟村不需要
 */
public class Address {
    /**
     * 省
     */
    @NotNull
    private String province;
    private String city;
    private String country;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
