package com.fisa.infra.comment.dto;

import lombok.*;
import lombok.extern.java.Log;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Builder
public class CommentDTO {

    private Long boardId;

    private Long accountId;

    // 댓글 조회 시 필요한 프로필 이미지
    private String imageUrl;

    private String loginId;

    // 대댓글 여부
    private boolean isParent; // true : 댓글, false : 대댓글

    // 부모 댓글 id
    private Long parentId;

    //내용
    private String content;

    //생성날짜
    private LocalDateTime createdAt;

    //수정날짜
    private LocalDateTime updatedAt;

    //삭제여부
    private boolean deleteYN;
}
