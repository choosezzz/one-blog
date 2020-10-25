package oneblog.web.controller;

import lombok.extern.log4j.Log4j2;
import oneblog.service.ArticleService;
import oneblog.service.UserService;
import oneblog.utils.ResponseUtil;
import oneblog.web.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/article")
    public ResponseVO<Long> allArticleCount(){
        return ResponseUtil.success(articleService.count());
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
