package oneblog.service;

import oneblog.model.Tags;

import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/9 0:26
 */
public interface TagsService {

    /**
     * 添加标签
     * @param tags
     * @return
     */
    boolean addTag(Tags tags);

    /**
     * 更新标签
     * @param tags
     * @return
     */
    boolean updateTags(Tags tags);

    /**
     * 删除标签
     * @param tags
     * @return
     */
    boolean deleteTag(Tags tags);

    List<Tags> getNormalTags();

    Tags getTagById(Integer tagId);

    Boolean tagExist(String tagName);

}
