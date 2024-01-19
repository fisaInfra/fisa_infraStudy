package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.repository.CommentRepository;
import com.fisa.infra.reply.domain.Reply;
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

    /**
     * 댓글 작성
     * @param articleNum
     * @param reply
     * @param loginId
     * @return 저장된 글
     */
    public Comment writeComment(Long articleNum, Reply reply, String loginId) throws RuntimeException{
        Optional<Account> op = accountRepository.findByLoginId(loginId);

        if (op.isEmpty()){
            throw new RuntimeException("해당 로그인 아이디를 가진 회원이  존재하지 않습니다.");
        }

        Account account = op.get();

//        Comment comment = Comment.builder()
//                .boardId()
//                .content()
//                .groupIndex()
//                .accountId(List.of(account))
//                .hierarchyIndex()
//                .build();

//        commentRepository.save(comment);

        return null;
    }
}