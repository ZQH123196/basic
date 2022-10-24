package db.d_orm.mybatis;

import db.d_orm.mybatis.mapper.User;
import db.d_orm.mybatis.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class mybatis_crud {

    SqlSessionFactory sqlSessionFactory;

    mybatis_crud(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    User dql() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            session.selectOne("db.d_orm.mybatis.mapper.UserMapper.getList", 0);
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.getOne(0L);
            return user;
        }
    }

}
