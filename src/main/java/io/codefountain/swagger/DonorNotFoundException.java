package io.codefountain.swagger;

public class DonorNotFoundException extends RuntimeException {

    public DonorNotFoundException(String message){
        super(message);
    }
}
