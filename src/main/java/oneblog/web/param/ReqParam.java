package oneblog.web.param;

import lombok.Getter;
import lombok.ToString;
import oneblog.utils.TraceUtil;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 10:52
 */
@Getter
@ToString
public class ReqParam {

    private String traceId;
    private long time;

    public ReqParam() {
        this.traceId = TraceUtil.getTraceId();
        this.time = System.currentTimeMillis();
    }
}
