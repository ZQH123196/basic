package com.example.validation.entity.group;

import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;

public class TestGroupBean {

    // 继承自 Default 就能让 Default 分组的也生效，update 不继承自 Default 就会允许负数存入了
    // 一定要继承自 Default
    public interface Add extends Default {}
    public interface Update {}
//    public interface Update extends Default {}

    /**
     *  初始化时 id 必须为 null
     *  更新时 id 不能为 null
     */
    @Null(groups = {Add.class})
    @Positive(groups = {Update.class})
    // Default group 的内容，默认都是 Default group
    @Min(0)
    private Long id;

    public TestGroupBean() {
    }

    public TestGroupBean(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
