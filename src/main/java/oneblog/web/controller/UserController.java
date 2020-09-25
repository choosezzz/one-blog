package oneblog.web.controller;

import com.sun.istack.internal.NotNull;
import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.ResponseUtil;
import oneblog.web.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:33
 */

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseVO<User> login(@RequestBody @NotNull User user){

        logger.error("param={}", user);
        User result = userService.getUserWithPasswordByParam(user);

        return ResponseUtil.success(result);
    }
}
