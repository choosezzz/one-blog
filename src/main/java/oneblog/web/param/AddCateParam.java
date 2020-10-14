package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author dingshuangen
 * @Date 2020/10/14 17:57
 */
@Getter
@Setter
@ToString(callSuper = true)
public class AddCateParam extends ReqParam{
    private String cateName;
}
