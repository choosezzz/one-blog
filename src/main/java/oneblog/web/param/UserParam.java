package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oneblog.web.param.ReqParam;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author dingshuangen
 * @Date 2020/9/28 14:58
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserParam extends ReqParam {

    @NotNull(message = "userId不可为空！")
    private Integer userId;
    private String userName;
    private String phone;
    private String email;
    private Integer sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String introduction;
    private Integer roleId;
}
