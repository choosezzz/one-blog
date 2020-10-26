package oneblog.service.impl;

import oneblog.dao.ArticleMapper;
import oneblog.model.Article;
import oneblog.model.Tags;
import oneblog.service.ArticleService;
import oneblog.web.response.vo.ArticleDetailVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 16:11
 */
@Service
@EnableTransactionManagement
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int writeArticle(Article article) {

        int insert = articleMapper.insertSelective(article);
        return insert;
    }

    @Override
    public int editArticle(Article article) {
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    public int deleteArticle(Integer id) {
        return 0;
    }

    @Override
    public List<ArticleDetailVO> articleList(Integer userId) {
        return articleMapper.selectDetailInfoList(userId);
    }

    @Override
    public ArticleDetailVO getArticleDetailById(Integer articleId, Integer userId) {
        ArticleDetailVO detailInfo = articleMapper.selectDetailInfoByIdWithUser(articleId, userId);
        if (detailInfo != null && StringUtils.isNotEmpty(detailInfo.getTags())) {
            String[] tags = detailInfo.getTags().split(",");
            List<Tags> tagsList = redisService.batchGetTags(Arrays.asList(tags));
            detailInfo.setTagList(tagsList);
        }
        return detailInfo;
    }

    @Override
    public ArticleDetailVO getArticleDetailById(Integer articleId) {
        ArticleDetailVO detailInfo = articleMapper.selectDetailInfoById(articleId);
        if (detailInfo != null && StringUtils.isNotEmpty(detailInfo.getTags())) {
            String[] tags = detailInfo.getTags().split(",");
            List<Tags> tagsList = redisService.batchGetTags(Arrays.asList(tags));
            detailInfo.setTagList(tagsList);
        }
        return detailInfo;
    }

    @Override
    public long count() {
        return articleMapper.count();
    }
}
