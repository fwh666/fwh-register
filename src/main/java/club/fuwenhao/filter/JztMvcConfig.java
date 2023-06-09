package club.fuwenhao.filter;

/**
 * @author hezhuoming
 * @version V1.0.0
 * @description:
 * @date 2020/4/29 11:15
 * @copyright Copyright  2019 智能城市icity.jd.com ALL Right Reserved
 */

import club.fuwenhao.intercept.AuthorizationInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@SpringBootConfiguration
@ConditionalOnProperty(name = "jzt.user.mvc.enable", havingValue = "true", matchIfMissing = true)
public class JztMvcConfig implements WebMvcConfigurer {


    @Resource
    AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
//        registry.addMapping("/**")
        registry.addMapping("/")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

    //    @Bean
//    public FilterRegistrationBean servletRegistrationBean() {
//        BaseFilter userInfoFilter = new BaseFilter();
//        FilterRegistrationBean<BaseFilter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(userInfoFilter);
//        bean.setName("userInfoFilter");
//        bean.addUrlPatterns("/api/users/login");
//        bean.addUrlPatterns("/users/login");
//        bean.addUrlPatterns("/api/users/register");
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
}
