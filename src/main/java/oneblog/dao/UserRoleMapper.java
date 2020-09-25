package oneblog.dao;

import oneblog.model.UserRole;

public interface UserRoleMapper {

    int insert(UserRole record);

    int insertSelective(UserRole record);
}