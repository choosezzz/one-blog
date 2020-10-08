package oneblog.web.controller;

import com.alibaba.fastjson.JSON;
import oneblog.constant.ResponseEnum;
import oneblog.model.Role;
import oneblog.service.RoleService;
import oneblog.service.impl.RedisService;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.RoleParam;
import oneblog.web.response.ResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/9/30 14:41
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private RedisService redisService;

    @GetMapping(value = "/list", name = "查看全部用户角色")
    public ResponseVO<List<Role>> roleList(HttpSession session) {
        logger.info("[userRoles]:traceId={}, seesionId={}, userId=", TraceUtil.getTraceId(), session.getId(), session.getAttribute("user"));
        List<Role> userRoles = roleService.roleList();
        logger.info("[userRoles]:list={}", JSON.toJSONString(userRoles));
        return ResponseUtil.success(userRoles);
    }

    @PostMapping("/add")
    public ResponseVO<Role> addRole(RoleParam param){
        logger.error("add role : param = {}" , param);
        String roleName = param.getRoleName();
        String roleId = redisService.getRoleIdByName(roleName);
        if (StringUtils.isNotEmpty(roleId)){
            return ResponseUtil.forNull(ResponseEnum.ROLE_EXIST);
        }
        Role role = roleService.getRoleByName(roleName);
        if (role != null){
            redisService.setRoleIdByName(role.getRoleName(), role.getRoleId());
            return ResponseUtil.forNull(ResponseEnum.ROLE_EXIST);
        }
        role = new Role();
        role.setRoleName(roleName);
        int i = roleService.addRole(role);
        if (i > 0){
            return ResponseUtil.success(role);
        }
        return ResponseUtil.forNull(ResponseEnum.ROLE_FAILED);
    }
}
