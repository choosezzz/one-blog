package oneblog.service;

import oneblog.model.Role;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 15:28
 */
public interface RoleService {

    List<Role> roleList();

    int updateRole(Role role);

    int deleteRole(Integer roleId);

    int addRole(Role role);
}
