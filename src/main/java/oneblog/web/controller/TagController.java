package oneblog.web.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.Tags;
import oneblog.service.TagsService;
import oneblog.utils.ResponseUtil;
import oneblog.web.param.TagParam;
import oneblog.web.response.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/9 1:05
 */
@RestController
@RequestMapping(value = "/tag", name = "标签管理接口")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    @Autowired
    private TagsService tagsService;

    @GetMapping(name = "查看正常标签列表", value = "/list")
    public ResponseVO<List<Tags>> tagList(){
        List<Tags> normalTags = tagsService.getNormalTags();
        return ResponseUtil.success(normalTags);
    }

    @PostMapping(name = "添加标签", value = "/add")
    public ResponseVO<Tags> addTag(TagParam param){
        logger.info("[add tag]: param = {}", param);
        Boolean exist = tagsService.tagExist(param.getTagName());
        if (exist){
            return ResponseUtil.forNull(ResponseEnum.TAG_EXIST);
        }

        Tags tags = new Tags();
        tags.setTagName(param.getTagName());
        tags.setCreatedTime(param.getCreatedTime());
        tags.setStatus(ApiConstant.TAG_NORMAL);
        boolean success = tagsService.addTag(tags);
        if (success){
            return ResponseUtil.success(tags);
        }
        return ResponseUtil.forNull(ResponseEnum.TAG_FAILED);
    }
}
