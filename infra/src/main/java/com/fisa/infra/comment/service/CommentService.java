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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
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
    @Cacheable(value = "comment", key = "#commentDTO", cacheManager = "contentCacheManager")
    public Comment writeComment(CommentDTO commentDTO) throws RuntimeException {
//        Optional<Account> account = accountRepository.findAccountByLoginId(commentDTO.getLoginId());
//        Optional<Board> board = boardRepository.findById(commentDTO.getBoardId());

        Account account = accountRepository.findAccountByLoginId(commentDTO.getLoginId())
                .orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

        Board board = boardRepository.findById(commentDTO.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

//        if (op.isEmpty()){ // 벌써 예외처리하려고???
//            throw new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다.");
//        }

        //dto에 comment 객체를 생성하던지, comment 객체 내부에 생성 메서드를 만들어서 저장하던지 해야함
        //근데 나는 엔티티 내부에 생성메서드 만들어서 리턴 값으로 댓글 만드는게 좋다고 생각함.
        //지금은 서비스 계층에서 만들건데 너가 수정해라.
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
}