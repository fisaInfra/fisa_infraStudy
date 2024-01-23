package com.fisa.infra.comment.domain.dto;

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
    //댓글아이디
    @Id
    private String commentId;

    //게시글아이디
    private String boardId;

    //회원아이디
    private String accountId;

    //내용
    private String content;

    //생성날짜
    private LocalDateTime createdAt;

    //수정날짜
    private LocalDateTime updatedAt;

    //삭제여부
    private boolean deleteYN;

}