package oneblog.web.controller;

import com.alibaba.fastjson.JSON;
import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.adm.UserParam;
import oneblog.web.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 19:13
 */
@RestController
@RequestMapping("/c")
public class CommonRestController {

    private static final Logger logger = LoggerFactory.getLogger(CommonRestController.class);
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user/info", name = "查看用户信息")
    public ResponseVO<User> userInfo(HttpSession session) {

        User user = null;
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

    @PostMapping(value = "/user/update", name = "更新用户数据")
    public ResponseVO<User> updateUserInfo(@RequestBody @Valid UserParam userParam, HttpSession session) {

        logger.info("updateUserInfo: param = {}", userParam);
        User user = new User();
        BeanUtils.copyProperties(userParam, user);
        int i = userService.updateByUserId(user);
        if (i <= 0){
            return ResponseUtil.forNull(ResponseEnum.OPERATION_FAILED);
        }
        session.setAttribute(ApiConstant.SESSION_USER, user);
        return ResponseUtil.success(user);
    }
}
