package oneblog.service.impl;

import oneblog.dao.ArticleCountMapper;
import oneblog.service.ArticleCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 16:47
 */
@Service
public class ArticleCountServiceImpl implements ArticleCountService {

    public static final Byte TYPE_CATE = 1;
    public static final Byte TYPE_TAG = 2;

    @Autowired
    private ArticleCountMapper countMapper;

    @Override
    public int incrementTagCount(Integer typeId, Integer count) {
        return countMapper.updateCount(typeId, TYPE_TAG, count);
    }

    @Override
    public int incrementTagCount(Set<Integer> typeIds, Integer count) {
        return countMapper.batchUpdateCount(typeIds, TYPE_TAG, count);
    }

    @Override
    public int incrementCateCount(Integer typeId, Integer count) {
        return countMapper.updateCount(typeId, TYPE_CATE, count);
    }

    @Override
    public List<Map<Integer, Integer>> articleCateCount() {
        return countMapper.selectByType(TYPE_CATE);
    }

    @Override
    public List<Map<Integer, Integer>> articleTagCount() {
        return countMapper.selectByType(TYPE_TAG);
    }
}
