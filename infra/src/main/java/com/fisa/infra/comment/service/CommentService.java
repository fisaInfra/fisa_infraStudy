package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.repository.jpa.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;
    /**
     * 댓글 작성
     * @param commentDTO
     * @return 저장된 글
     */
    public Comment writeComment(CommentDTO commentDTO) throws RuntimeException {
        Account account = accountRepository.findAccountByLoginId(commentDTO.getLoginId())
                .orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

        Board board = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .board(board)
                .account(account)
                .content(commentDTO.getContent())
                .build();

        if (!commentDTO.isParent() && commentDTO.getParentId() != null) {
            Comment parent = commentRepository.findById(commentDTO.getParentId()).orElseThrow(() -> new RuntimeException("부모 댓글을 찾을 수 없습니다."));
            comment.setParent(parent);
        }

        return commentRepository.save(comment);
    }

    /**
     * 댓글 조회
     * @param boardId
     * @return commentDTOList
     */
    public List<CommentDTO> readComment(Long boardId) throws RuntimeException {

        List<Comment> commentList = commentRepository.findCommentByBoardId(boardId);
        log.info("comment list : {}", commentList);

        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            log.info("comment content : {}", comment.getContent());

            // 댓글일 때
            boolean isParent = true;
            Long parentId = -1L; // 댓글의 parent id는 -1

            // 대댓글일 때
            if(comment.getParent() != null) {
                isParent = false;
                parentId = comment.getParent().getCommentId();
            }

            commentDTOList.add(
                    CommentDTO.builder()
                            .boardId(comment.getBoard().getBoardId())
                            .accountId(comment.getAccount().getAccountId())
                            .imageUrl(comment.getAccount().getImageUrl())
                            .loginId(comment.getAccount().getLoginId())
                            .isParent(isParent)
                            .parentId(parentId)
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedTime())
                            .updatedAt(comment.getModifiedTime())
                            .deleteYN(comment.getIsDeleted())
                            .build()
            );
        }

        return commentDTOList;
    }
}