package oneblog.web.controller;

import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.AuthResult;
import oneblog.web.param.LoginParam;
import oneblog.web.param.RegisterParam;
import oneblog.web.response.ResponseVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:33
 */
@RequestMapping("/auth")
@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
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
            //数据脱敏
            user.setPassword("-");
            user.setSalt("-");
            session.setAttribute("user", user);
            session.setAttribute("roleId", user.getRoleId());

        } catch (AuthenticationException e) {
            logger.error("[login-failed]:recordTime={}, traceId={}, userName={}, pwd={}", param.getTime(), param.getTraceId(), param.getUserName(), param.getPassword());
            return AuthResult.loginFailed();
        }
        return AuthResult.loginSuccess();
    }
    @ResponseBody
    @PostMapping("/register")
    public ResponseVO<User> register(@RequestBody @Valid RegisterParam param) {

        logger.error("param={}", param);

        //验证是否存在
        if (userService.existUser(param.getUserName())) {
            return AuthResult.userExist();
        }
        int insert = userService.registerUser(param);
        if (insert > 0) {
            return AuthResult.registerSuccess();
        }
        return AuthResult.registerFailed();
    }

    @RequestMapping(value = "/logout", name = "退出系统")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() || subject.isAuthenticated()) {
            subject.logout();
        }
        return "redirect:/c/index";
    }
}
