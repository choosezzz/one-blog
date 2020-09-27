package oneblog.web.param.api;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 19:19
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RegisterParam {

    @NotNull(message = "用户名不能为空！")
    private String userName;
    @NotNull(message = "密码不能为空！")
    private String password;

    private int sex = 0;
    private String phone;
    private String email;
}
