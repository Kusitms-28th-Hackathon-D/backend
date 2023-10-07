package com.groupD.server.controller.handler;

import com.groupD.server.domain.ErrorCode;
import com.groupD.server.domain.ErrorResponse;
import com.groupD.server.exception.auth.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleEmailExistsException(EmailExistsException e, HttpServletRequest request) {
        log.warn("Auth-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new ErrorResponse<>(ErrorCode.EMAIL_EXISTS_ERROR);
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleInvalidEmailException(InvalidEmailException e, HttpServletRequest request) {
        log.warn("Auth-002> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new ErrorResponse<>(ErrorCode.INVALID_EMAIL_ERROR);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleInvalidPasswordException(InvalidPasswordException e, HttpServletRequest request) {
        log.warn("Auth-003> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new ErrorResponse<>(ErrorCode.INVALID_PASSWORD_ERROR);
    }
}
