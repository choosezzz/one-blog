package oneblog.dao;

import oneblog.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer cateId);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer cateId);

    int updateByPrimaryKeySelective(Category record);
    List<Category> selectAll();

    int count();
}