package com.example.rbac0withgroup.dao.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Builder;

@Builder
public class RolePermission extends Model<RolePermission> {

  private String role;
  private long permissionId;


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }


  public long getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }

}
