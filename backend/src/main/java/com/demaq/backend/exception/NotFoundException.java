package com.demaq.backend.exception;

public class NotFoundException extends RestException{
    
    private static final long serialVersionUID = 1;

    public NotFoundException(){
        super();
    }

    public NotFoundException(ErrorDto errorDto){

        super(errorDto);
    }

}