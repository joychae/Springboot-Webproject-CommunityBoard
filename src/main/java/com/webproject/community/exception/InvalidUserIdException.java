package com.webproject.community.exception;

public class InvalidUserIdException extends RuntimeException {
    public InvalidUserIdException() { super("존재하지 않는 USER ID 입니다");}
}
