package oneblog.web.controller;

import oneblog.model.User;
import oneblog.service.UserRoleService;
import oneblog.service.UserService;
import oneblog.utils.ApiResult;
import oneblog.web.param.api.LoginParam;
import oneblog.web.param.api.RegisterParam;
import oneblog.web.response.ResponseVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:33
 */
@RequestMapping("/api")
@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/login")
    public ResponseVO<User> login(@RequestBody @Valid LoginParam param, HttpSession session) {
        //对传参进行转义
        String userName = HtmlUtils.htmlEscape(param.getUserName());
        param.setUserName(userName);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, param.getPassword(), param.isRememberMe());
        try {
            subject.login(token);
            User user = userService.getUserByName(userName);
            session.setAttribute("user", user);
            session.setAttribute("role", userRoleService.getUserRole(user.getUserId()));

        } catch (AuthenticationException e) {
            logger.error("[login-failed]:recordTime={}, traceId={}, userName={}, pwd={}", param.getTime(), param.getTraceId(), param.getUserName(), param.getPassword());
            return ApiResult.loginFailed();
        }
        return ApiResult.loginSuccess();
    }

    @PostMapping("/register")
    public ResponseVO<User> register(@RequestBody @Valid RegisterParam param) {

        logger.error("param={}", param);
        if (userService.existUser(param.getUserName())){
           return ApiResult.userExist();
        }
        userService.registerUser(param);
        return ApiResult.registerSuccess();
    }
}
