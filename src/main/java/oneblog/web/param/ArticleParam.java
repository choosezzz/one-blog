package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author dingshuangen
 * @Date 2020/10/24 19:57
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ArticleParam extends ReqParam {

    private Integer id;

    private String originalAuthor;

    private String originalTarget;

    @NotNull(message = "文章标题不可为空！")
    private String title;

    @NotNull(message = "文章类型不可为空！")
    private Byte type;
    @NotNull(message = "文章分类不可为空！")
    private Integer category;
    @NotNull(message = "文章标签不可为空！")
    private String tags;

    private String content;

}
