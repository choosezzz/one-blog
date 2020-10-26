package oneblog.web.controller;

import lombok.extern.slf4j.Slf4j;
import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.Category;
import oneblog.service.CateService;
import oneblog.utils.ResponseUtil;
import oneblog.utils.TraceUtil;
import oneblog.web.param.AddCateParam;
import oneblog.web.response.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 17:54
 */
@Slf4j
@RestController
@RequestMapping("/cate_m")
public class CateManageController {

    @Qualifier("CateManage")
    @Autowired
    private CateService cateService;

    @GetMapping("/list")
    public ResponseVO list(){
        return ResponseUtil.success(cateService.list());
    }
    @PostMapping("/add")
    public ResponseVO addCate(@RequestBody @Valid AddCateParam param){
        log.info("add cate, param:{}", param);
        Boolean exist = cateService.cateExist(param.getCateName());
        if (exist){
            return ResponseUtil.forNull(ResponseEnum.CATE_EXIST);
        }
        Category category = new Category();
        category.setCateName(param.getCateName());
        category.setCreatedTime(new Date());
        category.setStatus(ApiConstant.STATUS_NORMAL);
        int i = cateService.addCate(category);
        if (i > 0){
            return ResponseUtil.success();
        }
        return ResponseUtil.forNull(ResponseEnum.CATE_FAILED);
    }
    @GetMapping("/delete")
    public ResponseVO deleteCate(@RequestParam("cateId") Integer cateId){
        log.info("delete cate, traceId={}, param:{}", TraceUtil.getTraceId(), cateId);

        int i = cateService.deleteCate(cateId);
        if (i > 0){
            return ResponseUtil.success();
        }
        return ResponseUtil.forNull(ResponseEnum.CATE_FAILED);
    }
}
