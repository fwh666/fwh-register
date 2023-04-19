package club.fuwenhao.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class BaseFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ParameterRequestWrapper parameterRequestWrapper = null;
        try {
            if (servletRequest instanceof HttpServletRequest) {
                HttpServletRequest req = (HttpServletRequest) servletRequest;
                parameterRequestWrapper = new ParameterRequestWrapper(req);
            }
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setHeader("Access-Control-Allow-Origin", "*"); // 允许http://localhost:3000跨域请求数据
            response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE"); // 允许的请求方法
            response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允许的请求头

        } catch (Exception e) {
            log.warn("parameterRequestWrapper Error:", e);
        }
        filterChain.doFilter((Objects.isNull(parameterRequestWrapper) ? servletRequest : parameterRequestWrapper), servletResponse);
    }
}
