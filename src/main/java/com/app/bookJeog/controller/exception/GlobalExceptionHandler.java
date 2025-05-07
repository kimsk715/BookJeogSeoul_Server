package com.app.bookJeog.controller.exception;

import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.app.bookJeog.controller")
@Slf4j
public class GlobalExceptionHandler {
    
    // 로그인 안 했는데 회원 서비스를 시도할 때

    @ExceptionHandler(LoginFailException.class)
    protected String loginFail(LoginFailException e) {
        log.error(e.getMessage());
        return "/login/login?result=fail";
    }

    @ExceptionHandler(UnauthenticatedException.class)
    protected String UnauthenticatedException(UnauthenticatedException e) {
        log.error(e.getMessage());
        return "/login/login";
    }

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
