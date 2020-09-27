package oneblog.service;

import oneblog.model.User;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:14
 */
public interface UserService {

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    User getUserById(int userId);
    /**
     * 根据用户相关参数查询用户
     * @param user
     * @return
     */
    User getUserWithPasswordByParam(User user);

    int registerUser(User user);
}
