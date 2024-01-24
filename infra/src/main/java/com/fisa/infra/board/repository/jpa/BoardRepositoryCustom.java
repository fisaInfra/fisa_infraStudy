package com.fisa.infra.board.repository.jpa;

import com.fisa.infra.board.domain.dto.BoardDTO;

public interface BoardRepositoryCustom {
    BoardDTO findById(Long id);
}
