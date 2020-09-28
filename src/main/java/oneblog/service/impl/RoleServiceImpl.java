package oneblog.service.impl;

import oneblog.dao.RoleMapper;
import oneblog.model.Role;
import oneblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 15:29
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> roleList() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int deleteRole(Integer role) {
        return roleMapper.deleteByPrimaryKey(role);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }
}
