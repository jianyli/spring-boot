package com.li.advice;

import com.li.support.dto.RestResultDto;
import com.li.support.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class RestExceptionAdvice {
    private static final Logger log = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public RestResultDto<String> errorHandle(HttpServletRequest req, HttpServletResponse rep, Exception ex) {
        if (ex instanceof ServiceException) {
            ServiceException e = (ServiceException) ex;
            log.error("自定义异常",ex);
            return RestResultDto.newFail(e.getErrorCode(), ex.getMessage());
        } else {
            log.error("系统异常",ex);
            return RestResultDto.newFail("系统异常", ex.getMessage());
        }
    }
}
