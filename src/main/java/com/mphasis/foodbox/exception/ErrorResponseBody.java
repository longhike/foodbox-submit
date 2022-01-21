package com.mphasis.foodbox.exception;

import java.io.Serializable;

/**
* Object to contain status and message for user-defined errors
*
*/
public class ErrorResponseBody implements Serializable {

	private static final long serialVersionUID = -8546420395746460530L;
	String message;
    int statusCode;

    public ErrorResponseBody(String msg, int value) {
        this.message = msg;
        this.statusCode = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
