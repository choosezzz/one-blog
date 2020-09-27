package oneblog.realm;

import oneblog.model.User;
import oneblog.service.UserService;
import oneblog.service.impl.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class JPARealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(JPARealm.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = token.getPrincipal().toString();
        String userId = redisService.getUserIdByName(userName);
        User user;
        if(StringUtils.isEmpty(userId)){
            user = userService.getUserByName(userName);
        }else {
            user = userService.getUserById(Integer.parseInt(userId));
        }
        if (user == null) {
            return null;
        }
        redisService.setUserId(userName, String.valueOf(user.getUserId()));
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userId, passwordInDB, ByteSource.Util.bytes(salt),
                getName());
        return authenticationInfo;
    }
}

