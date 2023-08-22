package com.Aforv.aop.exceptionhandler;

public class InvalidUserException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidUserException(String s) {
        super(s);
    }
}
