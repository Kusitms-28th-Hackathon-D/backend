package com.groupD.server.controller.handler;

import com.groupD.server.domain.ErrorCode;
import com.groupD.server.domain.ErrorResponse;
import com.groupD.server.exception.member.MemberNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler(MemberNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleMemberNotExistsException(MemberNotExistsException e, HttpServletRequest request) {
        log.warn("Member-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new ErrorResponse<>(ErrorCode.MEMBER_NOT_EXISTS_ERROR);
    }
}
