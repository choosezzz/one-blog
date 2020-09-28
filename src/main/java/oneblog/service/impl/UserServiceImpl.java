package oneblog.service.impl;

import oneblog.constant.ApiConstant;
import oneblog.dao.UserMapper;
import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.ApiResult;
import oneblog.web.param.adm.UserParam;
import oneblog.web.param.api.RegisterParam;
import oneblog.web.response.ResponseVO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:18
 */
@Service
@EnableTransactionManagement
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public User getUserByIdWithPwd(int userId, String password) {
        return userMapper.selectByIdWithPwd(userId, password);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.selectById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseVO registerUser(RegisterParam param) {

        //验证是否存在
        if (existUser(param.getUserName())) {
            return ApiResult.userExist();
        }
        User user = new User();
        user.setUserName(HtmlUtils.htmlEscape(param.getUserName()));
        user.setAvatar(ApiConstant.DEFAULT_AVATAR);
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        //对密码进行md5加密
        String encodedPassword = new SimpleHash("md5", param.getPassword(), salt, times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        user.setRegisterTime(new Date());
        user.setStatus(ApiConstant.STATUS_NORMAL);
        user.setType(ApiConstant.TYPE_REGISTER);
        user.setPhone(param.getPhone());
        user.setEmail(param.getEmail());
        user.setSex(param.getSex());
        //默认角色
        user.setRoleId(ApiConstant.COMMON_USER);
        //写入用户数据
        int insert = userMapper.insertSelective(user);
        if (insert > 0) {
            return ApiResult.registerSuccess();
        }
        return ApiResult.registerFailed();
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public boolean existUser(String userName) {
        boolean exist = false;
        Integer userId = redisService.getUserIdByName(userName);
        if (userId == null || userId <= 0) {
            User user = getUserByName(userName);
            if (user != null) {
                redisService.setUserId(userName, user.getUserId());
                exist = true;
            }
        } else {
            exist = true;
        }
        return exist;
    }

    @Override
    public int updateByUserId(User user) {

        return userMapper.updateByPrimaryKeySelective(user);
    }
}
