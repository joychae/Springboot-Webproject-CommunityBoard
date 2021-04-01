package com.webproject.community.exception;

public class InvalidMemoIdException extends RuntimeException{
    public InvalidMemoIdException() { super("존재하지 않는 MEMO ID 입니다");}
}
