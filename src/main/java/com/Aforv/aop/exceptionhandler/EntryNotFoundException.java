package com.Aforv.aop.exceptionhandler;

import java.io.Serial;

public class EntryNotFoundException extends Exception{
    /**
	 * 
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public EntryNotFoundException(String s) {
        super(s);
    }
}
