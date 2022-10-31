package com.example.rbac0withgroup.dao.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;

public class Permission extends Model<Permission> {

  private long id;
  private String expression;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

}
