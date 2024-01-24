package com.fisa.infra.board.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.board.repository.querydsl.QueryBoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    QueryBoardRepository queryBoardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BoardService boardService;

    @BeforeEach
    //@Rollback(value = false)
    public void boardSave() {

        Account account = Account.builder()
                /* 이 부분은 스프링 시큐리티 컨텍스트 홀더에서 가져올 수 있을까요? */
                .loginId("onionhaseyo")
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

        Board board = Board.builder()
                .title("제목입니다")
                .content("내용입니다")
                .account(account)
                .build();

        boardRepository.save(board);

        Board board2 = Board.builder()
                .title("제목입니다22")
                .content("내용입니다22")
                .build();

        boardRepository.save(board2);

    }

    @Test
    public void getAllBoard() {

        List<Board> all = boardRepository.findAll();
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    public void getBoardById(){
        Optional<BoardDTO> optionalBoard = queryBoardRepository.queryFindBoardById(1L);

        assertThat(optionalBoard).isPresent(); // Board가 존재하는지 확인

        BoardDTO board = optionalBoard.get();
        // Account 엔티티가 연결되어 있는 경우 loginId 검증
        if (board.getLoginId() != null) {
            assertThat(board.getLoginId()).isEqualTo("onionhaseyo");
        }
    }


}