package com.example.rbac0.dao.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Role extends Model<Role> {

  @TableId
  private String role;
  private String username;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;



}
