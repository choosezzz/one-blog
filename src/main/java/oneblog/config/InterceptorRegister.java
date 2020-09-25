package oneblog.config;

import oneblog.web.intercepter.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author dingshuangen
 * @Date 2020/9/24 16:19
 * <p>
 * 注册拦截器
 */

@Configuration
public class InterceptorRegister implements WebMvcConfigurer {


    @Bean
    public UserInterceptor getUserInterceptor() {
        return new UserInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getUserInterceptor()).addPathPatterns("/**");
    }
}
