package oneblog.constant;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 19:55
 */
public class ApiConstant {
    /**
     * 超级管理员
     */
    public static final int SUPER_ADMIN = 1;
    /**
     * 普通管理员
     */
    public static final int ADMIN = 2;
    /**
     * 一般用户
     */
    public static final int COMMON_USER = 3;

    /**
     * 默认头像
     */
    public static String DEFAULT_AVATAR = "/img/face/default.jpg";
    public static final byte STATUS_NORMAL = 1; // 正常状态
    public static final byte STATUS_HIDDEN = 2; // 屏蔽状态
    public static final byte STATUS_CANCEL = 3; // 注销状态

    public static final byte TYPE_REGISTER = 1; // 注册用户
    public static final byte TYPE_VISITOR = 2;  // 访客

}
