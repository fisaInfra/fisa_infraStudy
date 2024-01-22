package com.fisa.infra.comment.dto;

import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class CommentDTO {

    //게시글아이디
    private Long boardId;

    //회원아이디
    private Long accountId;

    //내용
    private String content;

    //생성날짜
    private LocalDateTime createdAt;

    //수정날짜
    private LocalDateTime updatedAt;

    private Long parentId;
}
