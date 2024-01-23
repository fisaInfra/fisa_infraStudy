package com.fisa.infra.comment.dto;

import com.fisa.infra.comment.domain.Comment;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentDTO that = (CommentDTO) o;

        return isParent == that.isParent &&
                deleteYN == that.deleteYN &&
                Objects.equals(boardId, that.boardId) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(parentId, that.parentId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boardId, loginId, isParent, parentId, content, createdAt, updatedAt, deleteYN);
    }

}
