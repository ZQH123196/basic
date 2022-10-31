package com.example.rbac0withhierarchy.dao.entity;


public class RoleHierarchy {

  private String role;
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

}
