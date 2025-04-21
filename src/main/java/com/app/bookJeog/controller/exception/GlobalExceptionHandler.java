package com.app.bookJeog.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.app.bookJeog.controller")
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    protected String handleMypageSelectException(ResourceNotFoundException e){
        log.error(e.getMessage());
        return "/error/error";
    }

    @ExceptionHandler(RuntimeException.class)
    protected String RuntimeException(RuntimeException e){
        log.error(e.getMessage());
        return "/error/error";
    }
}
