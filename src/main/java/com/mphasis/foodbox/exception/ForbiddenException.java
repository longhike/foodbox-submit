package com.mphasis.foodbox.exception;

/**
* User-defined error for status 403
*
*/
public class ForbiddenException extends Exception {

	private static final long serialVersionUID = -152420931844996501L;
	private static final int statusCode = 403;

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(int statusCode) {
        super();
    }

    public ForbiddenException(String msg) {
        super(msg);
    }

    public ForbiddenException(String msg, int statusCode) {
        super(msg);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
    }

    @Override
    public String toString() {
        return "ForbiddenException{" + "status code = " + statusCode + "}";
    }

}
