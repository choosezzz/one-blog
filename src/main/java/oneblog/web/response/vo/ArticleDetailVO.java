package oneblog.web.response.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import oneblog.model.Category;
import oneblog.model.Tags;
import oneblog.model.User;

import java.util.Date;
import java.util.List;

/**
 * @Author dingshuangen
 * @Date 2020/10/25 16:05
 *
 * 文章详细信息
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDetailVO {

    private Integer id;

    private User author;

    private String originalAuthor;

    private String originalTarget;

    private String title;

    private Byte type;

    private Category category;

    private String tags;

    private List<Tags> tagList;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    private String content;
    private Integer likes = 0;
    private Integer views = 0;
}
