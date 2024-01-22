package com.fisa.infra.comment.dto;

import com.fisa.infra.comment.domain.Comment;
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