package oneblog.web.controller;

import lombok.extern.log4j.Log4j2;
import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.Article;
import oneblog.model.User;
import oneblog.service.ArticleService;
import oneblog.utils.AuthResult;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.ArticleParam;
import oneblog.web.response.ResponseVO;
import oneblog.web.response.vo.ArticleDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 19:54
 */
@RestController
@RequestMapping("/article")
@Log4j2
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/write", name = "发布新文章")
    public ResponseVO<Article> writeArticle(@RequestBody @Valid ArticleParam param, HttpSession session) {

        log.info("writeLog:{}", param);

        User user = getUserFromSession(session);
        if (user == null) {
            return AuthResult.userInfoEmpty();
        }
        Article article = new Article();
        article.setAuthor(user.getUserId());
        article.setCategory(param.getCategory());
        article.setContent(param.getContent());
        article.setOriginalAuthor(param.getOriginalAuthor());
        article.setOriginalTarget(param.getOriginalTarget());
        article.setPublishTime(new Date());
        article.setUpdateTime(new Date());
        article.setTitle(param.getTitle());
        article.setType(param.getType());
        article.setTags(param.getTags());
        article.setStatus(ApiConstant.STATUS_NORMAL);

        int i = articleService.writeArticle(article);
        log.info("writeLog,traceId = {}, result = {}", TraceUtil.getTraceId(), article);
        if (i > 0) {
            return ResponseUtil.success(article);
        }
        return ResponseUtil.forNull(ResponseEnum.ARTICLE_SAVE_FAILED);
    }

    @PutMapping(value = "/edit", name = "编辑文章")
    public ResponseVO<Article> editArticle(@RequestBody @Valid ArticleParam param, HttpSession session) {

        log.info("writeLog:{}", param);

        if (param.getId() == null || param.getId() <= 0) {
            return ResponseUtil.forNull(ResponseEnum.PARAM_INVALID);
        }

        User user = getUserFromSession(session);
        if (user == null) {
            return AuthResult.userInfoEmpty();
        }
        Article article = new Article();
        article.setId(param.getId());
        article.setCategory(param.getCategory());
        article.setContent(param.getContent());
        article.setOriginalAuthor(param.getOriginalAuthor());
        article.setOriginalTarget(param.getOriginalTarget());
        article.setUpdateTime(new Date());
        article.setTitle(param.getTitle());
        article.setType(param.getType());
        article.setTags(param.getTags());
        article.setStatus(ApiConstant.STATUS_NORMAL);

        int i = articleService.editArticle(article);
        log.info("writeLog,traceId = {}, result = {}", TraceUtil.getTraceId(), article);
        if (i > 0) {
            return ResponseUtil.success(article);
        }
        return ResponseUtil.forNull(ResponseEnum.ARTICLE_SAVE_FAILED);
    }

    @GetMapping("/list")
    public ResponseVO<List<ArticleDetailVO>> listArticle(HttpSession session) {
        User user = getUserFromSession(session);
        if (user == null) {
            return AuthResult.userInfoEmpty();
        }
        return ResponseUtil.success(articleService.articleList(user.getUserId()));
    }

    @GetMapping("/{id}")
    public ResponseVO<ArticleDetailVO> getArticle(@PathVariable(name = "id") Integer articleId, HttpSession session) {
        log.info("getArticle:id={},traceId={}", articleId, TraceUtil.getTraceId());
        User user = getUserFromSession(session);
        if (user == null) {
            return AuthResult.userInfoEmpty();
        }
        ArticleDetailVO detailInfo = articleService.getArticleDetailById(articleId, user.getUserId());
        return ResponseUtil.success(detailInfo);
    }

    @GetMapping("/view/{aid}")
    public ResponseVO<ArticleDetailVO> getViewArticle(@PathVariable(name = "aid") Integer articleId) {
        log.info("getArticle:id={},traceId={}", articleId, TraceUtil.getTraceId());
        ArticleDetailVO detailInfo = articleService.getArticleDetailById(articleId);
        return ResponseUtil.success(detailInfo);
    }

    private User getUserFromSession(HttpSession session) {
        Object attribute = session.getAttribute(ApiConstant.SESSION_USER);
        if (attribute == null) {
            return null;
        }
        return (User) attribute;
    }
}
