package oneblog.constant;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 18:01
 */
public enum ApiEnum {

    LOGIN_SUCCESS(6000, "登录成功！"),
    LOGIN_FAILED(6001, "用户名或密码错误！"),
    PARAM_INVALID(6002, "参数验证失败！"),


    ;


    private String msg;
    private int code;

    ApiEnum(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
}
