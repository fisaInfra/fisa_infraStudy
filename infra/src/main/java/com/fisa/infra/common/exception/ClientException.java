package com.fisa.infra.common.exception;



public class ClientException extends RuntimeException {

    private final ErrorCode errorCode;


    public ClientException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode=errorCode;

    }


}
