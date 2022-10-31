package com.example.rbac0withgroup.dao.entity;


public class UserGroupAdmin {

  private String group;
  private String roles;
  private String users;
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

}
