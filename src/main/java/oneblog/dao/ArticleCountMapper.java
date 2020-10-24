package oneblog.dao;

import oneblog.model.ArticleCount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface ArticleCountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleCount record);

    ArticleCount selectByPrimaryKey(Integer id);
    List<Map<Integer, Integer>> selectByType(Byte type);

    int updateCount(@Param("typeId") Integer typeId,@Param("type") Byte type, @Param("count") Integer count);
    int batchUpdateCount(@Param("typeIds") Set<Integer> typeIds, @Param("type") Byte type, @Param("count") Integer count);
}