package com.example.parking.exception;

public class InternalException extends Exception {
    private int code;

    public InternalException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
