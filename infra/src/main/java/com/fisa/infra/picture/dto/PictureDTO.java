package com.fisa.infra.picture.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PictureDTO {

    // board
    private String boardId;

    // picture
    private String pictureFileUrl;
    private String pictureUrl;
}
