package oneblog.service.impl;

import oneblog.dao.UserMapper;
import oneblog.model.User;
import oneblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User getUserWithPasswordByParam(User user) {
        return userMapper.selectWithPasswordBySelective(user);
    }
}
