package io.codefountain.swagger.exceptions;

public class DonorAlreadyExistsException extends RuntimeException {

    private String message;

    public DonorAlreadyExistsException(String message){
        super(message);
    }
}
