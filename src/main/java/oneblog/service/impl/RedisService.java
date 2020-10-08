package oneblog.service.impl;

import com.alibaba.fastjson.JSON;
import oneblog.model.Tags;
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

    private static final String USER_NAME_ID_MAPPING = "m_name_id_";
    private static final String ROLE_ID = "role_";
    private static final String TAG_ID = "tag_";

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

    public Integer getUserIdByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        String value = getValue(USER_NAME_ID_MAPPING + name);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public void setUserId(String name, Integer id) {
        if (StringUtils.isEmpty(name)) {
            return;
        }
        setKV(USER_NAME_ID_MAPPING + name, String.valueOf(id));
    }

    public String getRoleIdByName(String roleName) {
        if (StringUtils.isEmpty(roleName)) {
            return null;
        }
        return getValue(ROLE_ID + roleName);
    }

    public void setRoleIdByName(String roleName, Integer roleId) {
        if (StringUtils.isEmpty(roleName)) {
            return;
        }
        setKV(ROLE_ID + roleName, String.valueOf(roleId));
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

    public Tags getTag(String tagName) {
        String value = getValue(TAG_ID + tagName);
        if (StringUtils.isNotEmpty(value)){
            return JSON.parseObject(value, Tags.class);
        }
        return null;
    }

    public void setTag(Tags tags) {
        setKV(TAG_ID + tags.getTagName(), JSON.toJSONString(tags));
    }

    public void deleteTag(String tagName) {
        stringRedisTemplate.delete(TAG_ID + tagName);
    }
}
