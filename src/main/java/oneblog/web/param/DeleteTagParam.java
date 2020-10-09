package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author dingshuangen
 * @Date 2020/10/9 16:05
 */
@Getter
@Setter
@ToString(callSuper = true)
public class DeleteTagParam extends ReqParam{
    @NotNull(message = "标签id不可为空！")
    private Integer tagId;
    @NotNull(message = "标签名称不可为空！")
    private String tagName;
}
