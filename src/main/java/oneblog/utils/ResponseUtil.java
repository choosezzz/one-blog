package oneblog.utils;

import oneblog.constant.ResponseEnum;
import oneblog.web.response.ResponseVO;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 15:01
 */
public class ResponseUtil {

    public static <T> ResponseVO<T> success(T data){
        return new ResponseVO(ResponseEnum.SUCCESS, data);
    }

    public static <T> ResponseVO<T> error(T data){
        return new ResponseVO(ResponseEnum.ERROR, data);
    }
}
