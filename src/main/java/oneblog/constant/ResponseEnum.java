package oneblog.constant;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:03
 */
public enum ResponseEnum {
    SUCCESS(2001, "OK"),
    ERROR(4001, "ERROR"),
    PARAM_INVALID(4002, "参数检验失败。"),
    OPERATION_FAILED(4003, "操作失败，请稍后重试。"),



    ;


    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ResponseEnum(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
