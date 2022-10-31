package com.example.rbac1.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 父子权限表，parent_role_id 为 null 就代表是根节点，程序只允许管理员的 parent_role_id 为 null。
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@TableName("role_hierarchy")
public class RoleHierarchy implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("role")
    private String role;

    @TableField("parent_role_id")
    private String parentRoleId;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(String parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    @Override
    public String toString() {
        return "RoleHierarchy{" +
            "role=" + role +
            ", parentRoleId=" + parentRoleId +
        "}";
    }
}
