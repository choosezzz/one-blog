package oneblog.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import oneblog.constant.ApiEnum;
import oneblog.constant.ResponseEnum;

/**
 * @Author dingshuangen
 * @Date 2020/9/25 14:57
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> {

    private String msg;
    private Integer code;
    private T data;

    public ResponseVO() {
    }

    public ResponseVO(ApiEnum apiEnum) {
        this.code = apiEnum.getCode();
        this.msg = apiEnum.getMsg();
    }
    public ResponseVO(ApiEnum apiEnum, T data) {
        this.code = apiEnum.getCode();
        this.msg = apiEnum.getMsg();
        this.data = data;
    }
    public ResponseVO(ResponseEnum status, T data) {
        this.msg = status.getMsg();
        this.code = status.getCode();
        this.data = data;
    }

    public ResponseVO(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
