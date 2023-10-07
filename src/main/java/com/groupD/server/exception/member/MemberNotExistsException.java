package com.groupD.server.exception.member;

public class MemberNotExistsException extends RuntimeException{
    public MemberNotExistsException(String message) {
        super(message);
    }
}
