package oneblog.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author dingshuangen
 * @Date 2020/9/20 23:02
 */
@RestController
public class TestController {

    @GetMapping(value = "/ping")
    public String ping(){
        return "PONG : " + System.currentTimeMillis();
    }
}
