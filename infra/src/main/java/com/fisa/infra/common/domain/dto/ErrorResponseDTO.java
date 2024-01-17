package com.fisa.infra.common.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String message;

}
