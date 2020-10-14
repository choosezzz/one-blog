package oneblog.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import oneblog.model.Category;
import oneblog.model.Tags;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 16:17
 */
@Service
public class RedisService {

    private static final String USER_NAME_ID_MAPPING = "m_name_id_";
    private static final String ROLE_ID = "role_";
    private static final String TAG_ID = "tag_";
    private static final String ALL_CATE_MAP = "all_cate_map";
    private static final String CATE_NAME_PREFIX = "cate_name_";
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

    public Integer getTagId(String tagName) {
        String value = getValue(TAG_ID + tagName);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public void setTagId(Tags tags) {
        setKV(TAG_ID + tags.getTagName(), String.valueOf(tags.getTagId()));
    }

    public void deleteTagId(String tagName) {
        stringRedisTemplate.delete(TAG_ID + tagName);
    }

    public void batchSetTags(List<Tags> tags) {
        Map<String, String> map = tags.stream()
                .collect(Collectors.toMap(entry -> (TAG_ID + entry.getTagName()), entry -> String.valueOf(entry.getTagId())));
        stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 获取cateId
     *
     * @param cateName
     * @return
     */
    public Integer getCateId(String cateName) {
        String value = getValue(CATE_NAME_PREFIX + cateName);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    /**
     * 获取cate对象
     *
     * @param cateId
     * @return
     */
    public Category getCate(Integer cateId) {
        Object o = stringRedisTemplate.opsForHash().get(ALL_CATE_MAP, String.valueOf(cateId));
        if (o != null) {
            return JSON.parseObject((String) o, Category.class);
        }
        return null;
    }

    /**
     * 添加cate缓存
     *
     * @param cate
     */
    public void setCate(Category cate) {
        //cateName-cateId
        setKV(CATE_NAME_PREFIX + cate.getCateName(), String.valueOf(cate.getCateId()));
        stringRedisTemplate.opsForHash().put(ALL_CATE_MAP, String.valueOf(cate.getCateId()), JSON.toJSONString(cate));
    }

    public void deleteCate(Integer cateId) {
        Category cate = getCate(cateId);
        if (cate != null) {
            stringRedisTemplate.delete(CATE_NAME_PREFIX + cate.getCateName());
            stringRedisTemplate.opsForHash().delete(ALL_CATE_MAP, String.valueOf(cate.getCateId()));
        }
    }

    public void batchSetCate(List<Category> cateList) {
        stringRedisTemplate.delete(ALL_CATE_MAP);
        Map<String, String> map = cateList.stream().collect(Collectors.toMap(e->String.valueOf(e.getCateId()), e->JSON.toJSONString(e)));
        stringRedisTemplate.opsForHash().putAll(ALL_CATE_MAP, map);
    }
    public List<Category> batchGetCate() {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(ALL_CATE_MAP);
        if (MapUtils.isNotEmpty(entries)){
            return entries.values().stream().map(e->JSON.parseObject((String) e, Category.class)).collect(Collectors.toList());
        }
        return null;
    }
}
