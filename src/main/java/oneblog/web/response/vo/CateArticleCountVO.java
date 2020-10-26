package oneblog.web.response.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author dingshuangen
 * @Date 2020/10/26 11:51
 */
@Getter
@Setter
@ToString
public class CateArticleCountVO {

    private Integer cateId;
    private String cateName;
    private Integer articleCount = 0;
}
