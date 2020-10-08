package oneblog.web.intercepter;

import oneblog.constant.ApiConstant;
import oneblog.model.User;
import oneblog.utils.CookieUtil;
import oneblog.utils.IpUtil;
import oneblog.utils.TraceUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @Author dingshuangen
 * @Date 2020/9/24 16:16
 */
public class UserInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    private static final List<String> NO_AUTH = Arrays.asList("/c",  "/", "/c/", "/c/index");
    private static final List<String> SUPER_ADMIN = Arrays.asList("/m",  "/adm");


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TraceUtil.setTraceId(UUID.randomUUID().toString().replace("-", ""));
        //会话id
        String sessionId = CookieUtil.getCookie(request, ApiConstant.JSESSIONID);
        //获取IP地址
        String ipAddr = IpUtil.getIpAddr(request);
        //请求路径
        String contextPath = request.getSession().getServletContext().getContextPath();
        String uri = request.getRequestURI();
        uri = StringUtils.remove(uri, contextPath);
        return true;
        /*if (NO_AUTH.contains(uri)){
            return true;
        }else if (uri.startsWith("/m") || uri.startsWith("/adm")){
            Object attribute = request.getSession().getAttribute(ApiConstant.SESSION_USER);
            if (attribute != null && attribute instanceof User){
                User user = (User) attribute;
                if (user.getRoleId() == 1 || user.getRoleId() == 2){
                    return true;
                }
            }
            response.sendRedirect("/c/index");
            return false;
        }
        return false;*/
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
