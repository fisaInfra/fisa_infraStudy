package com.fisa.infra.upload.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileDto {
    //AWS URL
    private String url;

    //UUID로 생성된 이미지명
    private String fileName;

    public ProfileDto(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }
}
