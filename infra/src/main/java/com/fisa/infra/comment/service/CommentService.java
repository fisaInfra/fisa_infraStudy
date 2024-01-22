package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Comment writeComment(CommentDTO commentDTO) throws RuntimeException{
        Optional<Account> account = accountRepository.findById(1L);
        Optional<Board> board = boardRepository.findById(1L);

//        if (op.isEmpty()){ // 벌써 예외처리하려고???
//            throw new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다.");
//        }

        Comment comment = Comment.saveComment(commentDTO);
        comment.setAccount(account.get());
        comment.setBoard(board.get());

        //대댓글
        if(commentDTO.getParentId() != null) {
            Optional<Comment> parent = commentRepository.findById(commentDTO.getParentId());
            comment.setComment(parent.get());
        }
        //댓글
        commentRepository.save(comment);
        return null;
    }
}