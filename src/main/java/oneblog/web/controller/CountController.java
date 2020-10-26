package oneblog.web.controller;

import lombok.extern.log4j.Log4j2;
import oneblog.service.ArticleService;
import oneblog.service.CateService;
import oneblog.service.TagsService;
import oneblog.service.UserService;
import oneblog.utils.ResponseUtil;
import oneblog.web.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dingshuangen
 * @Date 2020/10/25 17:01
 */
@RestController
@RequestMapping("/count")
@Log4j2
public class CountController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Autowired
    private TagsService tagsService;

    @Qualifier("CateQuery")
    @Autowired
    private CateService cateService;


    @GetMapping("/article")
    public ResponseVO<Long> allArticleCount(){
        return ResponseUtil.success(articleService.count());
    }

    @GetMapping("/cate")
    public ResponseVO cateCount(){
        return ResponseUtil.success(cateService.count());
    }

    @GetMapping(value = "/tags", name = "统计标签数量")
    public ResponseVO<Integer> tagsCount() {
        Integer count = tagsService.getNormalTagsCount();
        return ResponseUtil.success(count);
    }

    @GetMapping("/cate_article")
    public ResponseVO cateArticleCountList(){
        return ResponseUtil.success(cateService.getArticleCountList());
    }

    @GetMapping("/tag_article/{tagId}")
    public ResponseVO tagArticleCount(@PathVariable("tagId") Integer tagId){
        return ResponseUtil.success(tagsService.getArticleCount(tagId));
    }

    @GetMapping("/cate_article/{cateId}")
    public ResponseVO cateArticleCount(@PathVariable("cateId") Integer cateId){
        return ResponseUtil.success(cateService.getArticleCount(cateId));
    }

    @GetMapping("/tag_article")
    public ResponseVO tagArticleCountList(){
        return ResponseUtil.success(tagsService.getArticleCountList());
    }

    /** TODO 添加访问相关接口*/

    @GetMapping("/visit")
    public ResponseVO<Long> allVisitCount(){
        return ResponseUtil.success(9999L);
    }
    @GetMapping("/yesterday")
    public ResponseVO<Long> yesterdayVisitCount(){
        return ResponseUtil.success(9999L);
    }
    @GetMapping("/user")
    public ResponseVO<Long> allUserCount(){
        return ResponseUtil.success(userService.count());
    }
    @GetMapping("/comments")
    public ResponseVO<Long> allCommentsCount(){
        return ResponseUtil.success(9999L);
    }
    @GetMapping("/browse")
    public ResponseVO<Long> allBrowseCount(){
        return ResponseUtil.success(9999L);
    }
    @GetMapping("/visitors")
    public ResponseVO<Long> allVisitorsCount(){
        return ResponseUtil.success(9999L);
    }

}
