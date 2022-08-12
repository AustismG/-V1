package com.example.task.advice;


import com.example.task.constant.ResultCodeEnum;
import com.example.task.exception.BusinessException;
import com.example.task.exception.IllegalOperationException;
import com.example.task.exception.NotFoundException;
import com.example.task.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public CommonResult handleBindException(BindException e, HttpServletRequest request) {
        String message = formatBindException(e);
        log.warn(formatException(e, request, message, false));
        return CommonResult.failure(ResultCodeEnum.PARAM_VALIDATE_FAILED.getCode(), message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult handleBindException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = formatBindException(e);
        log.warn(formatException(e, request, message, false));
        return CommonResult.failure(ResultCodeEnum.PARAM_VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 请求方式不支持
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonResult handleMethodNotAllowed(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("请求方式不支持");
    }


    /**
     * 请求格式不对
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ServletRequestBindingException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public CommonResult handleBadRequest(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("请求格式不对");
    }


    /**
     * 请求的接口权限不足，例如一个普通用户去请求管理员才能用的接口（这是授权的时候抛出的异常，在进入Controller之前就被拦住，更不会进入Service层）
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public CommonResult handleAccessDeniedException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("权限不足");
    }

    /**
     * 请求的操作非法，例如一个普通用户要删除其他普通用户发布的内容（已通过授权并进入了Controller层，但是在Service层发现操作非法而抛出的异常）
     */
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalOperationException.class)
    public CommonResult handleIllegalOperationException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, false));
        return CommonResult.failure("操作非法");
    }

    /**
     * 请求资源不存在
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public CommonResult handleNotFoundException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
        return CommonResult.failure("请求资源不存在");
    }

    /**
     * 业务异常，可细分为多种情况，可见ResultCodeEnum
     */
    @ExceptionHandler(BusinessException.class)
    public CommonResult handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
        return CommonResult.failure(e.getCode(), e.getMessage());
    }


    /**
     * 如果前面的处理器都没拦截住，最后兜底
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception e, HttpServletRequest request) {
        log.warn(formatException(e, request, null, true));
        return CommonResult.failure("服务器内部错误");
    }


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化Exception
     */
    public static String formatException(Exception e, HttpServletRequest request, String message, boolean stackRequired) {
        StringBuilder sb = new StringBuilder();
        sb.append("[出现异常]")
                .append("\n<请求>  ").append(request.getMethod()).append("  ").append(request.getRequestURI())
                .append("\n<类型>  ").append(e.getClass())
                .append("\n<信息>  ").append(message != null ? message : e.getMessage());
        if (stackRequired) {
            sb.append("\n<堆栈>\n");
            for (StackTraceElement traceElement : e.getStackTrace()) {
                sb.append("\tat ").append(traceElement).append("\n");
            }
        }
        return sb.toString();
    }


    /**
     * 把异常信息格式化成自己喜欢的格式，这个方法用于格式化BindException
     */
    public static String formatBindException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            //提示：error.getField()得到的是校验失败的字段名字，error.getDefaultMessage()得到的是校验失败的原因
            sb.append(error.getField())
                    .append("=[")
                    .append(error.getDefaultMessage())
                    .append("]  ");
        }
        return sb.toString();

    }

    public static String formatBindException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError error : fieldErrors) {
            //提示：error.getField()得到的是校验失败的字段名字，error.getDefaultMessage()得到的是校验失败的原因
            sb.append(error.getField())
                    .append("=[")
                    .append(error.getDefaultMessage())
                    .append("]  ");
        }
        return sb.toString();
    }
}