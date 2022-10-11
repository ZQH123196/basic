package com.example.validation.entity.group;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;

public class TestGroup {

    // 继承自
    public interface Add extends Default {}
    public interface Update extends Default {}

    /**
     *  初始化时 id 必须为 null
     *  更新时 id 不能为 null
     */
    @Null(groups = {Add.class})
    @NotNull(groups = {Update.class})
    // Default group 的内容，默认都是 Default group
    @Size(min = 0)
    private Long id;
}
