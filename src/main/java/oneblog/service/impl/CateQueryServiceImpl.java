package oneblog.service.impl;

import oneblog.model.Category;
import oneblog.service.CateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 17:43
 */
@Service(value = "CateQuery")
public class CateQueryServiceImpl implements CateService {

    @Autowired
    private RedisService redisService;
    @Override
    public int addCate(Category category) {
        return 0;
    }

    @Override
    public int deleteCate(Integer cateId) {
        return 0;
    }

    @Override
    public Category getCateById(Integer cateId) {
        return redisService.getCate(cateId);
    }

    @Override
    public List<Category> list() {
        return redisService.batchGetCate();
    }

    @Override
    public Boolean cateExist(String cateName) {
        return redisService.getCateId(cateName) != null;
    }
}