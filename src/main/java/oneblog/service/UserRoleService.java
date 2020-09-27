package oneblog.service;

import oneblog.model.UserRole;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 17:36
 */
public interface UserRoleService {

    int addUserRole(UserRole userRole);
    UserRole getUserRole(Integer userId);

    int updateUserRole(UserRole userRole);
}
