package com.fisa.infra.common.exception;

public class ServerException extends RuntimeException{


    private final ErrorCode errorCode;


    public ServerException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;

    }
}
