package oneblog.service;

import oneblog.model.User;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:14
 */
public interface UserService {

    /**
     * 根据用户id查询
     *
     * @param userId
     * @return
     */
    User getUserByIdWithPwd(int userId, String password);

    User getUserById(int userId);
    User getUserByName(String userName);

    int registerUser(User user);
}
