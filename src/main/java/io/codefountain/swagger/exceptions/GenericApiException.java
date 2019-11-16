package io.codefountain.swagger.exceptions;

public class GenericApiException extends RuntimeException {

    private String message;

    public GenericApiException(String message){
        super(message);
    }
}
