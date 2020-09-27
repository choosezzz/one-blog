package oneblog.service.impl;

import oneblog.dao.UserRoleMapper;
import oneblog.model.UserRole;
import oneblog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 17:38
 */
@Service
@EnableTransactionManagement
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUserRole(UserRole userRole) {
        int insert = userRoleMapper.insert(userRole);
        if (insert > 0) {
            redisService.setUserRoleId(userRole.getUserId(), userRole.getRoleId());
        }
        return insert;
    }

    @Override
    public UserRole getUserRole(Integer userId) {
        UserRole userRole;
        Integer roleId = redisService.getUserRoleId(userId);
        if (roleId == null || roleId <= 0) {
            userRole = userRoleMapper.selectUserRole(userId);
            if (userRole != null) {
                redisService.setUserRoleId(userRole.getUserId(), userRole.getRoleId());
            }
        } else {
            userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
        }
        return userRole;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserRole(UserRole userRole) {
        int update = userRoleMapper.updateUserRole(userRole);
        if (update > 0) {
            redisService.setUserRoleId(userRole.getUserId(), userRole.getRoleId());
        }
        return update;
    }
}
