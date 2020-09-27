package oneblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer userId;

    private String phone;

    private String userName;

    @JsonIgnore
    private String password;

    private int sex;

    private String realName;

    private Date birthday;

    private String email;

    private String introduction;

    @JsonInclude
    private String salt;

    private String avatar;

    private Date registTime;

    private Byte status;

    private Byte type;
}