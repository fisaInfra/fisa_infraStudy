package com.fisa.infra.board.domain.dto;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardRequestDTO {

//    // account
//    private String loginId;

    // board
    private String title;
    private String content;
//    private Date updatedAt;

}
