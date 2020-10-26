package oneblog.service.impl;

import oneblog.model.Category;
import oneblog.service.CateService;
import oneblog.web.response.vo.CateArticleCountVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("CateManage")
    @Autowired
    private CateService cateService;
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
        List<Category> allCate = redisService.getAllCate();
        if (CollectionUtils.isEmpty(allCate)){
            allCate = cateService.list();
        }
        return allCate;
    }

    @Override
    public Boolean cateExist(String cateName) {
        return redisService.getCateId(cateName) != null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public CateArticleCountVO getArticleCount(Integer cateId) {
        return cateService.getArticleCount(cateId);
    }

    @Override
    public List<CateArticleCountVO> getArticleCountList() {
        return cateService.getArticleCountList();
    }
}
