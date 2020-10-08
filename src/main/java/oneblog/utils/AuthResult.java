package oneblog.utils;

import oneblog.constant.ApiEnum;
import oneblog.web.response.ResponseVO;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 17:59
 */
public class AuthResult {

    public static ResponseVO loginSuccess() {
        return new ResponseVO(ApiEnum.LOGIN_SUCCESS);
    }
    public static ResponseVO registerSuccess() {
        return new ResponseVO(ApiEnum.REGISTER_SUCCESS);
    }
    public static ResponseVO registerFailed() {
        return new ResponseVO(ApiEnum.REGISTER_FAILED);
    }

    public static ResponseVO loginFailed() {
        return new ResponseVO(ApiEnum.LOGIN_FAILED);
    }

    public static ResponseVO invalid() {
        return new ResponseVO(ApiEnum.PARAM_INVALID);
    }

    public static ResponseVO userExist() {
        return new ResponseVO(ApiEnum.USER_EXIST);
    }
    public static ResponseVO uploadFailed() {
        return new ResponseVO(ApiEnum.UPLOAD_FAILED);
    }
    public static ResponseVO uploadEmpty() {
        return new ResponseVO(ApiEnum.UPLOAD_EMPTY);
    }
    public static ResponseVO<String> uploadSuccess(String filePath) {
        return new ResponseVO(ApiEnum.UPLOAD_SUCCESS, filePath);
    }
}
