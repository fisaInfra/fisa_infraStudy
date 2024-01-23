package com.fisa.infra.board.service;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardService boardService;

    @BeforeEach
    @Rollback(value = false)
    public void boardSave() {
        Board board = Board.builder()
                .title("제목입니다")
                .content("내용입니다")
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
        assertThat(all.size()).isEqualTo(3);
    }


}