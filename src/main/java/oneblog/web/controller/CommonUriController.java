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
        return "redirect:/index";
    }

    @GetMapping(value = "/article", name = "文章浏览")
    public String viewArticle() {
        return "/fore/show";
    }

    @GetMapping("/404")
    public String page404() {
        return "error/404";
    }

    @GetMapping(value = "/500")
    public String page500() {
        return "error/500";
    }
}
