package com.mphasis.foodbox.exception;

/**
* User-defined error for status 404
*
*/
public class NotFoundException extends Exception {

	private static final long serialVersionUID = 6180011558116840617L;
    private static final int statusCode = 404;

    public NotFoundException() {
        super();
    }

    public NotFoundException(int statusCode) {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, int statusCode) {
        super(msg);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
    }

    @Override
    public String toString() {
        return "NotFoundException{" + "status code = " + statusCode + "}";
    }

}
