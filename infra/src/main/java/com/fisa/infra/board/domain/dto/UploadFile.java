package com.fisa.infra.board.domain.dto;

import lombok.Data;

@Data
public class UploadFile {

    //원본 파일 이름
    private String uploadFileName;
    //파일이 저장될 때 파일 이름
    private String storeFileName;

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
