package com.Aforv.aop.exceptionhandler;

import java.io.Serial;

public class InvalidUserException extends RuntimeException{
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidUserException(String s) {
        super(s);
    }
}
