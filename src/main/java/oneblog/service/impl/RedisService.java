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

import java.util.*;
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
    private static final String ALL_TAG_MAP = "all_tag_map";
    private static final String ALL_CATE_MAP = "all_cate_map";
    private static final String CATE_NAME_PREFIX = "cate_name_";
    private static final String TAG_NAME_PREFIX = "tag_name_";
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

    private <T> T hget(String key, String field, Class<T> clazz) {
        Object o = stringRedisTemplate.opsForHash().get(key, field);
        if (o != null) {
            return JSON.parseObject((String) o, clazz);
        }
        return null;
    }

    private void put(String key, String field, String value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    private void putTags(Tags tags) {
        stringRedisTemplate.opsForHash().put(ALL_TAG_MAP, String.valueOf(tags.getTagId()), JSONObject.toJSONString(tags));
    }

    private void putCategory(Category category) {
        stringRedisTemplate.opsForHash().put(ALL_CATE_MAP, String.valueOf(category.getCateId()), JSONObject.toJSONString(category));
    }

    public Integer getTagsId(String tagsName) {
        String value = getValue(TAG_NAME_PREFIX + tagsName);
        if (StringUtils.isNotEmpty(value)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    public Tags getTags(Integer tagsId) {
        return hget(ALL_TAG_MAP, String.valueOf(tagsId), Tags.class);
    }

    public List<Tags> batchGetTags(List<Object> tagsIdList) {
        if (CollectionUtils.isEmpty(tagsIdList)){
            return null;
        }
        List<Object> objects = stringRedisTemplate.opsForHash().multiGet(ALL_TAG_MAP, tagsIdList);
        if (objects != null) {
            return objects.stream().map(e -> JSONObject.parseObject((String) e, Tags.class)).collect(Collectors.toList());
        }
        return null;
    }

    public void addTags(Tags tags) {
        if (tags != null) {
            setKV(TAG_NAME_PREFIX + tags.getTagName(), String.valueOf(tags.getTagId()));
            putTags(tags);
        }
    }

    public void deleteTags(Tags tags) {
        stringRedisTemplate.delete(TAG_NAME_PREFIX + tags.getTagName());
        stringRedisTemplate.opsForHash().delete(ALL_TAG_MAP, String.valueOf(tags.getTagId()));
    }

    public void batchAddTags(List<Tags> tagsList) {
        if (CollectionUtils.isEmpty(tagsList)) {
            return;
        }
        Map<String, String> idMap = new HashMap<>();
        Map<String, String> nameMap = new HashMap<>();
        for (Tags e : tagsList) {
            idMap.put(String.valueOf(e.getTagId()), JSON.toJSONString(e));
            nameMap.put(TAG_NAME_PREFIX + e.getTagName(), String.valueOf(e.getTagId()));
        }
        stringRedisTemplate.opsForHash().putAll(ALL_TAG_MAP, idMap);
        stringRedisTemplate.opsForValue().multiSet(nameMap);
    }

    public void addAllTags(List<Tags> tagsList) {
        if (CollectionUtils.isEmpty(tagsList)) {
            return;
        }
        stringRedisTemplate.delete(ALL_TAG_MAP);
        batchAddTags(tagsList);
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

    public void setAllCate(List<Category> cateList) {
        stringRedisTemplate.delete(ALL_CATE_MAP);
        Map<String, String> map = cateList.stream().collect(Collectors.toMap(e -> String.valueOf(e.getCateId()), e -> JSON.toJSONString(e)));
        stringRedisTemplate.opsForHash().putAll(ALL_CATE_MAP, map);
    }

    public List<Category> getAllCate() {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(ALL_CATE_MAP);
        if (MapUtils.isNotEmpty(entries)) {
            return entries.values().stream().map(e -> JSON.parseObject((String) e, Category.class)).collect(Collectors.toList());
        }
        return null;
    }
}
