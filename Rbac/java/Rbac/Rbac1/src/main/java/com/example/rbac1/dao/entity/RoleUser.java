package com.example.rabc1.dao.entity;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class RoleUser extends Model<RoleUser> {

  private String role;
  private String username;



}
