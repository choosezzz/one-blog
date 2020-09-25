package oneblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author dingshuangen
 * @Date 2020/9/20 22:58
 *
 * MapperScan：mybatis扫描的映射文件所在包
 * SpringBootApplication：springboot启动入口注解
 */
@ServletComponentScan
@MapperScan("oneblog.dao")
@SpringBootApplication
public class OneBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneBlogApplication.class, args);
    }

}