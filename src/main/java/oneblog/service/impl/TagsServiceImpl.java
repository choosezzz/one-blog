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
            redisService.addTags(tags);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTags(Tags tags) {

        int i = tagsMapper.updateByPrimaryKeySelective(tags);
        if (i > 0) {
            redisService.addTags(tags);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean realDeleteTag(Tags tags) {
        int i = tagsMapper.deleteByPrimaryKey(tags.getTagId());
        if (i > 0) {
            redisService.deleteTags(tags);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTag(Tags tags) {
        int i = tagsMapper.updateByPrimaryKeySelective(tags);
        if (i > 0) {
            redisService.deleteTags(tags);
            return true;
        }
        return false;
    }

    @Override
    public List<Tags> getNormalTags() {
        List<Tags> tags = tagsMapper.selectAll(ApiConstant.STATUS_NORMAL);
        redisService.addAllTags(tags);
        return tags;
    }

    @Override
    public Tags getTagById(Integer tagId) {
        return tagsMapper.selectByPrimaryKey(tagId);
    }

    @Override
    public Boolean tagExist(String tagName) {

        //先查询缓存
        Integer tagId = redisService.getTagsId(tagName);
        if (tagId != null && tagId > 0) {
            return true;
        }
        //查询数据库
        Tags tags = tagsMapper.selectByTagName(tagName);
        if (tags != null) {
            //写入缓存
            redisService.addTags(tags);
            return true;
        }
        return false;
    }

    @Override
    public List<Tags> batchGetTags(List<Integer> tagIds) {
        return tagsMapper.batchGetTagsByPrimaryKey(tagIds);
    }

    @Override
    public Integer getNormalTagsCount() {
        return tagsMapper.count();
    }
}
