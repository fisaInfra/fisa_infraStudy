package com.fisa.infra.board.service;

import com.fisa.infra.account.domain.Account;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.querydsl.QueryBoardRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    QueryBoardRepository queryBoardRepository;

    private Board board1;
    private Board board2;
    private Account account;

    @BeforeEach
    void setup(){
        board1 = Board.builder()
                .title("제목입니다")
                .content("내용입니다")
                .account(account)
                .build();

        board2 = Board.builder()
                .title("제목입니다22")
                .content("내용입니다22")
                .account(account)
                .build();

    }

    @DisplayName("게시글 id로 사용자 login id 가져오기")
    @Test
    void getBoardById(){
        //given
        entityManager.persist(board1);
        entityManager.persist(account);

        //when
        Optional<BoardDTO> op = queryBoardRepository.queryFindBoardById(board1.getBoardId());

        //then
        assertThat(op.isPresent());

        BoardDTO board = op.get();
        // Account 엔티티가 연결되어 있는 경우 loginId 검증

        if (board.getLoginId() != null) {
            assertThat(board.getLoginId()).isEqualTo("testId");
        }

    }
}
