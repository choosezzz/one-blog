package oneblog.service;

import oneblog.model.Article;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 16:07
 */
public interface ArticleService {

    /**
     * 写文章
     * @param article
     * @return
     */
    int writeArticle(Article article);

    /**
     * 编辑文章
     * @param article
     * @return
     */
    int editArticle(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    int deleteArticle(Integer id);

    /**
     * 文章列表
     * @return
     */
    List<Article> articleList();
}
