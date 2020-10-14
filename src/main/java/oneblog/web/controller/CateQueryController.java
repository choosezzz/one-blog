package oneblog.web.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import oneblog.model.Category;
import oneblog.service.CateService;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 17:49
 */
@RestController
@RequestMapping("/cate")
@Slf4j
public class CateQueryController {

    @Qualifier("CateQuery")
    @Autowired
    private CateService cateService;

    @GetMapping("/list")
    public ResponseVO<List<Category>> list(){
        log.info("cate query list:traceId = {}", TraceUtil.getTraceId());
        List<Category> list = cateService.list();
        log.info("cate query list:traceId = {}, result = {}", TraceUtil.getTraceId(), JSON.toJSONString(list));

        return ResponseUtil.success(list);
    }
}
