package db.d_orm.mybatis.mapper;


import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    public User getOne(Long id);

    @Select("select * from sys_user where id=#{id}} ")
    public User getOneAnnotation(Long id);
}
