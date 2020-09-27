package oneblog.utils;

import oneblog.constant.ApiEnum;
import oneblog.web.response.ResponseVO;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 17:59
 */
public class ApiResult {

    public static ResponseVO success() {
        return new ResponseVO(ApiEnum.LOGIN_SUCCESS);
    }

    public static ResponseVO failed() {
        return new ResponseVO(ApiEnum.LOGIN_FAILED);
    }

    public static ResponseVO invalid() {
        return new ResponseVO(ApiEnum.PARAM_INVALID);
    }
}
