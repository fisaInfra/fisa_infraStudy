package com.fisa.infra.repository.basic;

import com.fisa.infra.repository.common.CommonBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fisa.infra.domain.entity.Board;
import org.springframework.data.repository.Repository;

public interface BoardRepository extends Repository<Board,Long>, CommonBoardRepository {

}
