package oneblog.utils;

/**
 * @Author dingshuangen
 * @Date 2020/9/27 14:46
 */
public class TraceUtil {

    private static final ThreadLocal<String> traceLocal = new ThreadLocal<>();

    public static void setTraceId(String traceId){
        traceLocal.set(traceId);
    }

    public static String getTraceId(){
        return traceLocal.get();
    }
}
