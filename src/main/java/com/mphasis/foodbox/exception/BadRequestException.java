package com.mphasis.foodbox.exception;

/**
* User-defined error for status 400
*
*/
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 2525804654512096519L;
	private static final int statusCode = 400;

    public BadRequestException() {
        super();
    }

    public BadRequestException(int statusCode) {
        super();
    }

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String msg, int statusCode) {
        super(msg);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
    }

    @Override
    public String toString() {
        return "BadRequestException{" + "status code = " + statusCode + "}";
    }
}
