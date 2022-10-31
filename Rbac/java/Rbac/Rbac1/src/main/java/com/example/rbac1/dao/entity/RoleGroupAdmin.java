package com.example.rbac1.dao.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.rbac1.dao.handle.ListStringConvertHandle;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 角色组表
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Data
@TableName(value = "role_group_admin", autoResultMap = true)
public class RoleGroupAdmin extends Model {

    @TableId("`group`")
    private String group;

    @TableField(typeHandler = ListStringConvertHandle.class)
    private List<String> roles;

    @TableField(typeHandler = ListStringConvertHandle.class)
    private List<String> users;

    @TableField("`separator`")
    private String separator;

    @Override
    public String toString() {
        return "RoleGroupAdmin{" +
                "group=" + group +
                ", roles=" + roles +
                ", users=" + users +
                ", separator=" + separator +
                "}";
    }

}
