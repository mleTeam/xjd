package com.dx.xjd.exception;

public class MleAuthenticationException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MleAuthenticationException(String msg, Throwable t) {
	    super(msg, t);
	}

    public MleAuthenticationException(String msg) {
        super(msg);
    }
	
}
