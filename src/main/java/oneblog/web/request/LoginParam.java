package oneblog.web.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 10:53
 */
@Getter
@Setter
@ToString(callSuper = true)
public class LoginParam extends ReqParam {

    @NotNull(message = "用户名不能为空！")
    private String userName;
    @NotNull(message = "密码不能为空！")
    private String password;
    private boolean rememberMe = false;
}
