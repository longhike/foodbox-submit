package com.mphasis.foodbox.exception;


/**
* User-defined error for status 401
*
*/
public class UnauthorizedException extends Exception {

	private static final long serialVersionUID = 4053620004527690166L;
	private static final int statusCode = 401;

    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(int statusCode) {
        super();
    }

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String msg, int statusCode) {
        super(msg);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
    }

    @Override
    public String toString() {
        return "UnauthorizedException{" + "status code = " + statusCode + "}";
    }
}
