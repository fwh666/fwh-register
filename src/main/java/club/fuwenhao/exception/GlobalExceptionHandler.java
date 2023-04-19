package club.fuwenhao.exception;


import club.fuwenhao.result.RespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author caowenjun1
 * @date 2022-06-06 20:02
 */
@Slf4j
@RestControllerAdvice
@Order(1)
public class GlobalExceptionHandler {
    @ExceptionHandler(AppBusinessException.class)
    public RespEntity customerExceptionHandler(AppBusinessException e) {
        log.error("业务处理异常 code：{} message :{}", e.getCode(), e.getMessage());
        return RespEntity.failure(e.getCode(), e.getMessage(), e.getData());
    }
}
