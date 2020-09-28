package oneblog.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Integer sex;

    private String realName;


    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;

    private String email;

    private String introduction;

    @JsonIgnore
    private String salt;

    private String avatar;

    private Date registerTime;

    private Integer roleId;

    private Byte status;

    private Byte type;
}