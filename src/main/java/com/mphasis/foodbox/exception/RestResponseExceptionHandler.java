package com.mphasis.foodbox.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
* Controller advice object to dispatch user defined errors
*
*/
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestException e, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        logger.error("Illegal request state occurred", e);
        String msg = e.getMessage().isEmpty() ? "Illegal request" : e.getMessage();
        ErrorResponseBody errorResponse = new ErrorResponseBody(msg, status.value());
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException e, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        logger.error("Illegal request state occurred", e);
        String msg = e.getMessage().isEmpty() ? "Illegal request" : e.getMessage();
        ErrorResponseBody errorResponse = new ErrorResponseBody(msg, status.value());
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Object> handleUserNotFoundException(ForbiddenException e, WebRequest request) {

        HttpStatus status = HttpStatus.FORBIDDEN;
        logger.error("Illegal request state occurred", e);
        String msg = e.getMessage().isEmpty() ? "Illegal request" : e.getMessage();
        ErrorResponseBody errorResponse = new ErrorResponseBody(msg, status.value());
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UnauthorizedException e, WebRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        logger.error("Illegal request state occurred", e);
        String msg = e.getMessage().isEmpty() ? "Illegal request" : e.getMessage();
        ErrorResponseBody errorResponse = new ErrorResponseBody(msg, status.value());
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), status, request);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllOtherException(Exception e, WebRequest request) {

        logger.error("Error occurred", e);
        ErrorResponseBody errorResponse = new ErrorResponseBody("System Error", 500);
        return handleExceptionInternal(e, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
