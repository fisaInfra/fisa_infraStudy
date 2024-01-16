package com.fisa.infra.board.repository.jpa;

import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.basic.CommonBoardRepository;
import org.springframework.data.repository.Repository;

public interface BoardRepository extends Repository<Board,Long>, CommonBoardRepository {

}
