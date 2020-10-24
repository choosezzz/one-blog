package oneblog.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 16:38
 */
public interface ArticleCountService {


    int incrementTagCount(Integer typeId, Integer count);
    int incrementTagCount(Set<Integer> typeIds, Integer count);

    int incrementCateCount(Integer typeId, Integer count);
    /**
     * 文章按照类型统计数量 cateId-count
     * @return
     */
    List<Map<Integer, Integer>> articleCateCount();

    /**
     * 按照标签
     * @return tagId-count
     */
    List<Map<Integer, Integer>> articleTagCount();
}
