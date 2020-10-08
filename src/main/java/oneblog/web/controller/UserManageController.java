package oneblog.web.controller;

import com.alibaba.fastjson.JSON;
import oneblog.constant.ResponseEnum;
import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.UserParam;
import oneblog.web.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 14:22
 */
@RestController
@RequestMapping("/um")
public class UserManageController {

    private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list", name = "查看全部用户列表")
    public ResponseVO<List<User>> userList(HttpSession session){
        logger.info("[userList]:traceId={}, seesionId={}, userId=", TraceUtil.getTraceId(), session.getId(),session.getAttribute("userId"));
        List<User> allUser = userService.getAllUser();
        logger.info("[userList]:list={}", JSON.toJSONString(allUser));
        return ResponseUtil.success(allUser);
    }

    @PostMapping(value = "/authorize", name = "用户权限变更")
    public ResponseVO<Integer> authorize(@RequestBody @Valid UserParam userParam){

        logger.error("userParam={}",userParam);
        if (userParam.getRoleId() == null || userParam.getRoleId() <= 0){
            return ResponseUtil.forNull(ResponseEnum.PARAM_INVALID);
        }
        User user = new User();
        user.setRoleId(userParam.getRoleId());
        user.setUserId(userParam.getUserId());
        int i = userService.updateByUserId(user);
        if (i > 0){
            return ResponseUtil.success(userParam.getRoleId());
        }
        return ResponseUtil.forNull(ResponseEnum.OPERATION_FAILED);
    }


}