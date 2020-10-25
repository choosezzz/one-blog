package oneblog.dao;

import oneblog.model.Article;
import oneblog.web.response.vo.ArticleDetailVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);
    List<Article> selectAll();

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    ArticleDetailVO selectDetailInfoById(Integer articleId);
    List<ArticleDetailVO> selectDetailInfoList();
    long count();
}