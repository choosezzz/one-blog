package oneblog.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 16:17
 */
@Service
public class RedisService {

    private static final String USER_NAME_ID = "un_id_";
    private static final String USER_ROLE_ID = "ur_id_";

    private static final long ONE_DAY = 24 * 60 * 60;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String get(String key) {
        return getValue(key);
    }

    public void set(String key, String value) {
        setKV(key, value);
    }

    public void setex(String key, String value, long expire) {
        setExpireKV(key, value, expire);
    }

    public String getUserIdByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return getValue(USER_NAME_ID + name);
    }

    public void setUserId(String name, String id) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        setKV(USER_NAME_ID + name, id);
    }

    public void setUserRoleId(Integer userId, Integer roleId) {
        setKV(USER_ROLE_ID + userId, String.valueOf(roleId));
    }

    public Integer getUserRoleId(Integer userId) {
        String value = getValue(USER_ROLE_ID + userId);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    private void setKV(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    private void setExpireKV(String key, String value, long expire) {
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    private String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
