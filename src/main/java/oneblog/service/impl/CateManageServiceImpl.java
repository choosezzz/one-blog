package oneblog.service.impl;

import oneblog.dao.CategoryMapper;
import oneblog.model.Category;
import oneblog.service.CateService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 16:34
 */
@Service(value = "CateManage")
@EnableTransactionManagement
public class CateManageServiceImpl implements CateService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCate(Category category) {
        int i = categoryMapper.insertSelective(category);
        if (i > 0){
            redisService.setCate(category);
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCate(Integer cateId) {
        int i = categoryMapper.deleteByPrimaryKey(cateId);
        if (i > 0){
            redisService.deleteCate(cateId);
        }
        return i;
    }

    @Override
    public Category getCateById(Integer cateId) {
        Category category = categoryMapper.selectByPrimaryKey(cateId);
        if (category != null){
            redisService.setCate(category);
        }
        return category;
    }
    @Override
    public List<Category> list() {
        List<Category> categories = categoryMapper.selectAll();
        if (CollectionUtils.isNotEmpty(categories)){
            redisService.batchSetCate(categories);
        }
        return categories;
    }

    @Override
    public Boolean cateExist(String cateName) {
        return redisService.getCateId(cateName) != null;
    }
}
