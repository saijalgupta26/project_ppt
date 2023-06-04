package com.example.mevenproject.exception;

import java.io.Serial;

public class CustomerNotFoundException extends  Exception{

    @Serial
    private static final long serialVersionUID = 1L;
    public CustomerNotFoundException(String error) {
        super(error);
    }
}
