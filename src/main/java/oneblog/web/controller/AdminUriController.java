package oneblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 11:00
 * <p>
 * 后台管理uri跳转控制器
 */
@RequestMapping("/m")
@Controller
public class AdminUriController {


    @GetMapping("/admin")
    public String adminIndex() {
        return "admin/admin";
    }

    @GetMapping(value = "/article")
    public String article() {
        return "admin/article";
    }

    @GetMapping(value = "/write_article")
    public String writeArticle() {
        return "admin/write_article";
    }

    @GetMapping(value = "/edit_article")
    public String editArticle() {
        return "admin/edit_article";
    }

    @GetMapping(value = "/like")
    public String like() {
        return "admin/like";
    }

    @GetMapping(value = "/category")
    public String category() {
        return "admin/category";
    }

    @GetMapping(value = "/tag")
    public String tag() {
        return "admin/tag";
    }

    @GetMapping(value = "/link")
    public String link() {
        return "admin/link";
    }

    @GetMapping(value = "/feedback")
    public String feedback() {
        return "admin/feedback";
    }

    @GetMapping(value = "/message")
    public String message() {
        return "admin/message";
    }

    @GetMapping(value = "/comment")
    public String comment() {
        return "admin/comment";
    }

    @GetMapping(value = "/user")
    public String user() {
        return "admin/user";
    }
}
