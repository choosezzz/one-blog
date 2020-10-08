package oneblog.web.controller;

import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.AuthResult;
import oneblog.utils.FileUtil;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.UserParam;
import oneblog.web.response.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 19:13
 */
@RestController
@RequestMapping(value = "/user", name = "用户相关操作接口")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "/info", name = "查看用户信息")
    public ResponseVO<User> userInfo(HttpSession session) {

        User user;
        Object attribute = session.getAttribute(ApiConstant.SESSION_USER);
        if (attribute != null && attribute instanceof User) {
            user = (User) attribute;
        } else {
            return ResponseUtil.forNull(ResponseEnum.PARAM_INVALID);
        }
        logger.info("userInfo: old user={}", user);
        user = userService.getUserById(user.getUserId());
        user.setSalt("-");
        user.setPassword("-");
        //更新session信息
        session.setAttribute(ApiConstant.SESSION_USER, user);
        logger.info("userInfo: traceId={}, new user={}", TraceUtil.getTraceId(), user);
        return ResponseUtil.success(user);
    }

    @PostMapping(value = "/update", name = "更新用户数据")
    public ResponseVO<User> updateUserInfo(@RequestBody @Valid UserParam userParam, HttpSession session) {

        logger.info("updateUserInfo: param = {}", userParam);
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        //该接口不允许更新用户角色
        Integer roleId = user.getRoleId();
        user.setRoleId(null);
        int i = userService.updateByUserId(user);
        if (i <= 0){
            return ResponseUtil.forNull(ResponseEnum.OPERATION_FAILED);
        }
        user.setRoleId(roleId);
        session.setAttribute(ApiConstant.SESSION_USER, user);
        return ResponseUtil.success(user);
    }

    @PostMapping(value = "/avatar", name = "修改头像")
    public ResponseVO<String> changeAvatar(MultipartFile avatar, HttpSession session) {
        User user;
        Object attribute = session.getAttribute(ApiConstant.SESSION_USER);
        if (attribute != null && attribute instanceof User) {
            user = (User) attribute;
        } else {
            return ResponseUtil.forNull(ResponseEnum.PARAM_INVALID);
        }
        logger.info("userInfo: old user={}", user);
        if (avatar == null || avatar.isEmpty()){
            return AuthResult.uploadEmpty();
        }

        String path = FileUtil.writeFile(avatar);
        logger.error("upload file, path = {}", path);
        if (StringUtils.isNotEmpty(path)){
            User newUser = new User();
            newUser.setUserId(user.getUserId());
            newUser.setAvatar(path);
            int i = userService.updateByUserId(newUser);
            if (i > 0){
                user.setAvatar(path);
                session.setAttribute(ApiConstant.SESSION_USER, user);
                return AuthResult.uploadSuccess(path);
            }
        }
        return AuthResult.uploadFailed();
    }
}
