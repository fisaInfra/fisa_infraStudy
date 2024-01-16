package com.fisa.infra.board.repository.basic;


import com.fisa.infra.board.domain.Board;

/*
* 해당 인터페이스를 통해서 메서드를 명시할 때 사용합니다.
* */
public interface CommonBoardRepository {

    Board findById(Long id);

}
