package org.king.exception;

import lombok.extern.slf4j.Slf4j;
import org.king.pojo.Result;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理器
 * 捕获所有异常，统一返回 Result JSON 格式，避免出现 Tomcat 默认 500 页面
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 缺少请求参数（@RequestParam 必传参数未传）
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingParam(MissingServletRequestParameterException e) {
        String msg = "缺少必要参数：" + e.getParameterName();
        log.warn(msg, e);
        return Result.error(msg);
    }

    /**
     * 请求参数类型不匹配（比如传了字符串给 Integer）
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleTypeMismatch(MethodArgumentTypeMismatchException e) {
        String msg = "参数类型错误：" + e.getName() + " 应为 " + (e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "正确类型");
        log.warn(msg, e);
        return Result.error(msg);
    }

    /**
//     * 请求体解析失败（JSON 格式错误、缺少字段）
//     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleNotReadable(HttpMessageNotReadableException e) {
        log.warn("请求体解析失败", e);
        return Result.error("请求体格式错误，请检查 JSON 内容");
    }
    /*
    * 运行异常
    * */

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e){
        return  Result.error(e.getMessage());
    }
    /**
     * 参数校验 / 非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgument(IllegalArgumentException e) {
        log.warn("非法参数：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 兜底：捕获所有其他未处理的异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleAll(Exception e) {
        log.error("服务器内部错误", e);
        // 返回统一错误，避免暴露堆栈信息给前端
        return Result.error("服务器内部错误，请稍后重试或联系管理员");
    }
}
