package oneblog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 17:38
 */
@Controller
public class IndexController {

    @GetMapping(value = "/", name = "重定向到默认页")
    public String index1() {
        return "redirect:/c/index";
    }
    @GetMapping(value = "/c/", name = "重定向到默认页")
    public String index2() {
        return "redirect:/c/index";
    }
    @GetMapping(value = "/c", name = "重定向到默认页")
    public String index3() {
        return "redirect:/c/index";
    }
    @GetMapping(value = "/m/", name = "重定向到默认页")
    public String index4() {
        return "redirect:/m/admin";
    }
    @GetMapping(value = "/m", name = "重定向到默认页")
    public String index5() {
        return "redirect:/m/admin";
    }
}
