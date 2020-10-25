package oneblog.dao;

import oneblog.model.Tags;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagId);

    List<Tags> batchGetTagsByPrimaryKey(@Param("tagIds") List<Integer> tagIds);

    Tags selectByTagName(String tagName);
    List<Tags> selectAll(Byte status);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);

    Integer count();
}