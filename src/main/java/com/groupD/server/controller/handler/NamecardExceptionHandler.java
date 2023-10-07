package com.groupD.server.controller.handler;

import com.groupD.server.domain.ErrorCode;
import com.groupD.server.domain.ErrorResponse;
import com.groupD.server.exception.member.MemberNotExistsException;
import com.groupD.server.exception.namecard.KeywordNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class NamecardExceptionHandler {
    @ExceptionHandler(KeywordNotExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<?> handleKeywordNotExistsException(KeywordNotExistsException e, HttpServletRequest request) {
        log.warn("Namecard-001> 요청 URI: " + request.getRequestURI() + ", 에러 메세지: " + e.getMessage());
        return new ErrorResponse<>(ErrorCode.KEYWORD_NOT_EXISTS_ERROR);
    }
}
