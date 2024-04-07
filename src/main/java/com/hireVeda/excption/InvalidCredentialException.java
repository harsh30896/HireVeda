package com.hireVeda.excption;

public class InvalidCredentialException extends RuntimeException {
    public InvalidCredentialException(String s) {
        super(s);
    }
}
