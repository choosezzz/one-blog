package oneblog.web.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @Author dingshuangen
 * @Date 2020/9/30 15:03
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RoleParam extends ReqParam {

    @NotNull(message = "角色名称不可为空")
    private String roleName;
    private Integer roleId;
}
