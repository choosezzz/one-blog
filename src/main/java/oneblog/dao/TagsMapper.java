package oneblog.dao;

import oneblog.model.Tags;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagId);
    Tags selectByTagName(String tagName);
    List<Tags> selectAll(Byte status);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);
}