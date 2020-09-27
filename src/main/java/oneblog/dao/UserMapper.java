package oneblog.dao;

import oneblog.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByIdWithPwd(@Param("userId") Integer userId, @Param("password") String password);
    User selectById(Integer userId);
    User selectByName(String name);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}