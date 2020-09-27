package oneblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 10:36
 * <p>
 * 普通页面链接跳转控制器
 */
@RequestMapping(value = "/c", name = "统一前缀")
@Controller
public class CommonUriController {

    @GetMapping(value = "/index", name = "默认页")
    public String index() {
        return "/fore/index";
    }

    @GetMapping(value = "/", name = "重定向到默认页")
    public String home() {
        return "redirect:/c/index";
    }

    @GetMapping(value = "/article", name = "文章浏览")
    public String viewArticle() {
        return "/fore/show";
    }

    @GetMapping(value="/register")
    public String register(){
        return "/fore/register";
    }
    @GetMapping(value="/login")
    public String login() {
        return "fore/login";
    }
    @GetMapping(value="/page404")
    public String page404() {
        return "error/404";
    }
    @GetMapping(value="/page500")
    public String page500() {
        return "error/500";
    }
    @GetMapping(value="/categories")
    public String categories() {
        return "fore/categories";
    }
    @GetMapping(value="/date")
    public String date() {
        return "fore/date";
    }
    @GetMapping(value="/tags")
    public String tags() {
        return "fore/tags";
    }
    @GetMapping(value="/article_of_tag")
    public String articleOfTag() {
        return "fore/article_of_tag";
    }
    @GetMapping(value="/friendlylink")
    public String friendLink() {
        return "fore/link";
    }
    @GetMapping(value="/search")
    public String search() {
        return "fore/search";
    }
    @GetMapping(value="/activity")
    public String activity() {
        return "fore/activity";
    }
    @GetMapping(value="/message")
    public String message() {
        return "fore/message";
    }
    @GetMapping(value="/home")
    public String page(){
        return "/fore/home";
    }
}
