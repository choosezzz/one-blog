package oneblog.dao;

import oneblog.model.Tags;

public interface TagsMapper {
    int deleteByPrimaryKey(Integer tagId);

    int insert(Tags record);

    int insertSelective(Tags record);

    Tags selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tags record);

    int updateByPrimaryKey(Tags record);
}