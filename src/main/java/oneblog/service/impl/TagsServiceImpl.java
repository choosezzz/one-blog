package oneblog.service.impl;

import oneblog.constant.ApiConstant;
import oneblog.dao.TagsMapper;
import oneblog.model.Tags;
import oneblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/9 0:29
 */
@Service
@EnableTransactionManagement
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addTag(Tags tags) {

        int i = tagsMapper.insertSelective(tags);
        if (i > 0) {
            redisService.setTag(tags);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTags(Tags tags) {

        int i = tagsMapper.updateByPrimaryKeySelective(tags);
        if (i > 0) {
            redisService.setTag(tags);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTag(Tags tags) {

        int i = tagsMapper.deleteByPrimaryKey(tags.getTagId());
        if (i > 0) {
            redisService.deleteTag(tags.getTagName());
            return true;
        }
        return false;
    }

    @Override
    public List<Tags> getNormalTags() {
        return tagsMapper.selectAll(ApiConstant.TAG_NORMAL);
    }

    @Override
    public Tags getTagById(Integer tagId) {
        return tagsMapper.selectByPrimaryKey(tagId);
    }

    @Override
    public Boolean tagExist(String tagName) {

        //先查询缓存
        Tags tag = redisService.getTag(tagName);
        if (tag != null) {
            return true;
        }
        //查询数据库
        tag = tagsMapper.selectByTagName(tagName);
        if (tag != null) {
            //写入缓存
            redisService.setTag(tag);
            return true;
        }
        return false;
    }
}
