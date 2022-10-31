package com.example.rbac0withgroup.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.rbac0withgroup.dao.handle.ListStringConvertHandle;
import lombok.Data;

import java.util.List;

@Data
@TableName(autoResultMap = true)
public class RoleGroupAdmin extends Model {

    @TableId("`group`")
    private String group;

    @TableField(typeHandler = ListStringConvertHandle.class)
    private List<String> roles;

    @TableField(typeHandler = ListStringConvertHandle.class)
    private List<String> users;

    @TableField("`separator`")
    private String separator;


}
