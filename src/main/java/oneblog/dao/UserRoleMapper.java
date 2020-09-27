package oneblog.dao;

import oneblog.model.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {

    int insert(UserRole record);

    UserRole selectUserRole(int userId);
    int updateUserRole(UserRole userRole);
}