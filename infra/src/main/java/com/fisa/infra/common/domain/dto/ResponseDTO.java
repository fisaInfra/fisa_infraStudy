package com.fisa.infra.common.domain.dto;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor(staticName = "of")
public class ResponseDTO<T> {

    private int status;
    private String message;
    private T data;

}
