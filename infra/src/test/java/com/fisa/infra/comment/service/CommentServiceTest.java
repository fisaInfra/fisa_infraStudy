package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.dto.CommentDTO;
import com.fisa.infra.comment.repository.jpa.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CommentServiceTest {

   @Autowired
    CommentService commentService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;


    //이거 말고 junit 5 + Mockito 사용하셔서 서비스 테스트 해주세요

    //@BeforeEach
    @Rollback(value = false) //이걸 하면 데이터가 롤백이 된다.
    public void accountSave() {
        //회원 생성
        Account account = Account.builder()
                /* 이 부분은 스프링 시큐리티 컨텍스트 홀더에서 가져올 수 있을까요? */
                .loginId("김어진")
                .pwd("nonghyupeunhang")
                .name("김어진")
                .belong("우리FISA")
                .gender(true)
                .imageUrl("사진URL입니다.-> AWS에 올라간 사진도 전부 String입니다.")
                .stack("난 스택이 싫어")
                .portfolio("깃허브url입니다.")
                .job("백수")
                .build();
        accountRepository.save(account);

        //게시판 생성
        Board board = Board.builder()
                .title("안녕하세요 게시판")
                .content("이것은 내용입니다.")
                .build();
        boardRepository.save(board);
    }

    //@Test
    public void commentSave() {

        //given
        CommentDTO commentDto = new CommentDTO();
        commentDto.setContent("댓글입니다.");
        commentDto.setBoardId(1L);
        commentDto.setLoginId("김어진");
        commentDto.setDeleteYN(false);
        commentDto.setParentId(202L);

        //when
        Comment comment = commentService.writeComment(commentDto);

        //then
        assertThat(comment.getCommentId()).isEqualTo(2L);
    }

    @Test
    public void 댓글_삭제(){

        //given
        Optional<Comment> comment = commentRepository.findById(252L);

        //when
        commentService.deleteComment(comment.get().getCommentId());

        //then
        assertThat(comment.get().getCommentId()).isNull();
    }
}