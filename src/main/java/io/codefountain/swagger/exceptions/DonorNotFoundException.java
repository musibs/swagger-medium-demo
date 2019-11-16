package io.codefountain.swagger.exceptions;

public class DonorNotFoundException extends RuntimeException {

    public DonorNotFoundException(String message){
        super(message);
    }
}
