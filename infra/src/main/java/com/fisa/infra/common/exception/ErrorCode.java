package com.fisa.infra.common.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonFormat
public enum ErrorCode {

    // 400
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "올바르지 않은 요청입니다."),


    // 401
    UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "인증되지 않은 회원입니다."),


    // 403
    FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN.value(),"접근 권한이 없는 요청입니다."),


    // 404
    NOT_FOUND_ACCOUNT(HttpStatus.NOT_FOUND.value(), "올바르지 않은 요청입니다."),




    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "처리 불가한 내부 오류 에러입니다."),

    //501
    NOT_IMPLEMENTED(HttpStatus.NOT_IMPLEMENTED.value(), "서버가 지원하지 않는 요청입니다."),


    // 503
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE.value(), "요청을 처리할 준비가 된것이 없습니다.")

    ;

    private int status;
    private String message;


}
