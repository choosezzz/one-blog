package oneblog.constant;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 18:01
 */
public enum ApiEnum {

    LOGIN_SUCCESS(6000, "登录成功！"),
    LOGIN_FAILED(6001, "用户名或密码错误！"),
    PARAM_INVALID(6002, "参数验证失败！"),
    USER_EXIST(6003, "该用户已存在！"),
    REGISTER_SUCCESS(6004, "注册成功！"),
    REGISTER_FAILED(6005, "注册失败，请稍后重试！"),
    UPLOAD_FAILED(6006, "文件上传失败，请稍后重试！"),
    UPLOAD_SUCCESS(6007, "文件上传成功！"),
    UPLOAD_EMPTY(6008, "上传文件为空，请选择文件后上传！"),
    USER_SESSION_EMPTY(6009, "未获取到用户登录信息！"),


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
