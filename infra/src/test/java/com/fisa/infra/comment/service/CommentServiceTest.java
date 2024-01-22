package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/*
*  * 현재 데이터가 없어서 어떻게 해야할 지 모를 거 같아서 테스트 코드를 간단하게 짜줌
 * 이건 통합테스트로 불리는데 강사님이 junit4로 해주셨어서 jnuit4로 해줌
 * 이렇게 더미 데이터를 넣으면서 코드를 짜는게 TDD라고 불림.
 *
 * */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceTest {

    //의존성을 주입 스프링은 객체를 모두 빈으로 관리해서 사용자가 필요할 때마다
    //가져와서 사용할 수 있게 만들어줌. -> 의존성 주입 이건 면접에서도 나오는 질문이니 가지고 가자.
    @Autowired
    CommentService commentService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BoardRepository boardRepository;

    @Before
    @Rollback(value = false) //이걸 하면 데이터가 롤백이 된다.
    public void accountSave() {
        //회원 생성
        Account account = Account.builder()
                .accountId(1L)
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
                .boardId(1L)
                .title("안녕하세요 게시판")
                .content("이것은 내용입니다.")
                .build();

        boardRepository.save(board);
    }

    @Test
    public void commentSave() {
        Optional<Account> account = accountRepository.findById(1L);
        Optional<Board> board = boardRepository.findById(1L);
        commentService.writeComment(board.get().getBoardId(), account.get().getLoginId());
    }
}