package oneblog.web.response.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author dingshuangen
 * @Date 2020/10/26 14:56
 */
@Getter
@Setter
@ToString
public class TagArticleCountVO {

    private String tagId;
    private String tagName;
    private Integer articleCount = 0;
}
