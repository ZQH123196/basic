package com.example.rbac1.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户组表
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@TableName("user_group_admin")
public class UserGroupAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("group")
    private String group;

    @TableField("roles")
    private String roles;

    @TableField("users")
    private String users;

    @TableField("separator")
    private String separator;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public String toString() {
        return "UserGroupAdmin{" +
            "group=" + group +
            ", roles=" + roles +
            ", users=" + users +
            ", separator=" + separator +
        "}";
    }
}
