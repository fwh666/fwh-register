package club.fuwenhao.intercept;

import club.fuwenhao.exception.AppBusinessCode;
import club.fuwenhao.exception.AppBusinessException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.jdcity.joycity.annotation.NoNeedLogin;
//import com.jdcity.joycity.domain.entity.JoycityAccount;
//import com.jdcity.joycity.enums.AccountDataSourceEnum;
//import com.jdcity.joycity.enums.AccountStatusEnum;
//import com.jdcity.joycity.util.CacheKeyUtil;
//import com.jdcity.joycity.util.Jsons;
//import com.jdcity.joycity.util.JwtUtil;
//import com.jdcity.jzt.common.exception.BusinessCode;
//import com.jdcity.jzt.common.exception.BusinessException;
//import com.jdcity.jzt.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Order(5)
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        NoNeedLogin annotation;
//        HandlerMethod handlerMethod;
//        if (handler instanceof HandlerMethod) {
////            annotation = ((HandlerMethod) handler).getMethodAnnotation(NoNeedLogin.class);
//            handlerMethod = ((HandlerMethod) handler);
//        } else {
//            return true;
//        }
//        if (annotation != null) {
//            return true;
//        }
        // swagger通行
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/swagger-resources") || requestURI.startsWith("/swagger-ui.html") || requestURI.startsWith("/webjars/") || "/error".equals(requestURI)) {
            return true;
        }
        //登录-注册通行
        if (requestURI.startsWith("/users/login") || requestURI.startsWith("/users/register")) {
            return true;
        }
        //验证通行
//        if (requestURI.startsWith("/users/auth")) {
//            return true;
//        }
        //校验token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            log.error("用户未登陆");
            throw new AppBusinessException(AppBusinessCode.INVALID_TOKEN);
        }
        if (!redisTemplate.hasKey(token)) {
            throw new AppBusinessException(AppBusinessCode.INVALID_TOKEN);
        }
        return true;
    }

    /**
     * token 校验
     *
     * @param request
    //     */
//    private boolean checkToken(HttpServletRequest request) {
//        // token校验
//        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (StringUtils.isEmpty(token)) {
//            log.error("用户未登陆");
//            return false;
//        }
//        return true;
//    }
}
