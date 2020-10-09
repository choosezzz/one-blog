package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author dingshuangen
 * @Date 2020/10/9 1:10
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AddTagParam extends ReqParam{

    @NotNull(message = "标签名称不可为空")
    private String tagName;
    private Date createdTime = new Date();
}
