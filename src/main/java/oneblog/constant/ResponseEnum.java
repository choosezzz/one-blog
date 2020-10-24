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
    ROLE_EXIST(4004, "角色名称已存在。"),
    ROLE_FAILED(4005, "角色操作失败。"),
    TAG_EXIST(4006, "标签已存在。"),
    TAG_FAILED(4007, "标签操作失败。"),
    TAG_NOT_MATCH(4008, "标签ID与名称不匹配。"),
    CATE_EXIST(4009, "类别已存在。"),
    CATE_FAILED(4010, "类别操作失败。"),
    CATE_NOT_MATCH(4011, "类别ID与名称不匹配。"),
    ARTICLE_SAVE_FAILED(4012, "文章保存失败。"),
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
