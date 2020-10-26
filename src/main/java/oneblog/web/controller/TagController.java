package oneblog.web.controller;

import oneblog.constant.ApiConstant;
import oneblog.constant.ResponseEnum;
import oneblog.model.Tags;
import oneblog.service.TagsService;
import oneblog.service.impl.RedisService;
import oneblog.utils.ResponseUtil;
import oneblog.web.param.AddTagParam;
import oneblog.web.param.DeleteTagParam;
import oneblog.web.response.ResponseVO;
import oneblog.web.response.vo.TagArticleCountVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private RedisService redisService;

    @GetMapping(name = "查看正常标签列表", value = "/list")
    public ResponseVO<List<Tags>> tagList() {
        List<Tags> normalTags = tagsService.getNormalTags();
        return ResponseUtil.success(normalTags);
    }

    @PostMapping(name = "添加标签", value = "/add")
    public ResponseVO<Tags> addTag(@RequestBody @Valid AddTagParam param) {
        logger.info("[add tag]: param = {}", param);
        Boolean exist = tagsService.tagExist(param.getTagName());
        if (exist) {
            return ResponseUtil.forNull(ResponseEnum.TAG_EXIST);
        }

        Tags tags = new Tags();
        tags.setTagName(param.getTagName());
        tags.setCreatedTime(param.getCreatedTime());
        tags.setStatus(ApiConstant.STATUS_NORMAL);
        boolean success = tagsService.addTag(tags);
        if (success) {
            return ResponseUtil.success(tags);
        }
        return ResponseUtil.forNull(ResponseEnum.TAG_FAILED);
    }
    //@PostMapping(value = "/delete", name = "逻辑删除")
    public ResponseVO deleteTags(@RequestBody @Valid DeleteTagParam param) {
        logger.info("[delete tag]: param = {}", param);

        Integer tagId = redisService.getTagsId(param.getTagName());
        if (tagId == null || !tagId.equals(param.getTagId())) {
            return ResponseUtil.forNull(ResponseEnum.TAG_NOT_MATCH);
        }
        TagArticleCountVO articleCount = tagsService.getArticleCount(tagId);
        if (articleCount != null && articleCount.getArticleCount() > 0){
            return ResponseUtil.forNull(ResponseEnum.TAG_NOT_EMPTY);
        }
        Tags tags = new Tags();
        tags.setTagId(param.getTagId());
        tags.setTagName(param.getTagName());
        tags.setStatus(ApiConstant.STATUS_DELETE);
        boolean success = tagsService.deleteTag(tags);
        if (success) {
            return ResponseUtil.success();
        }
        return ResponseUtil.forNull(ResponseEnum.TAG_FAILED);
    }

    @PostMapping(value = "/delete", name = "真实删除")
    public ResponseVO realDeleteTags(@RequestBody @Valid DeleteTagParam param) {
        logger.info("[real delete tag]: param = {}", param);
        Integer tagId = redisService.getTagsId(param.getTagName());
        if (tagId == null || !tagId.equals(param.getTagId())) {
            return ResponseUtil.forNull(ResponseEnum.TAG_NOT_MATCH);
        }
        TagArticleCountVO articleCount = tagsService.getArticleCount(tagId);
        if (articleCount != null && articleCount.getArticleCount() > 0){
            return ResponseUtil.forNull(ResponseEnum.TAG_NOT_EMPTY);
        }
        Tags tags = new Tags();
        tags.setTagId(tagId);
        tags.setTagName(param.getTagName());
        boolean success = tagsService.realDeleteTag(tags);
        if (success) {
            return ResponseUtil.success();
        }
        return ResponseUtil.forNull(ResponseEnum.TAG_FAILED);
    }
}
