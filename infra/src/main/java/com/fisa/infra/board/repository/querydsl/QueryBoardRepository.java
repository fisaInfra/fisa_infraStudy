package com.fisa.infra.board.repository.querydsl;

import com.fisa.infra.board.domain.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface QueryBoardRepository {
    Optional<BoardDTO> queryFindBoardById(Long id);

}
