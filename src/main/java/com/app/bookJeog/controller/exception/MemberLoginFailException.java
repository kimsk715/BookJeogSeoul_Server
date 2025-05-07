package com.app.bookJeog.controller.exception;

public class MemberLoginFailException extends RuntimeException {
    public MemberLoginFailException(String message) {
        super(message);
    }
}
