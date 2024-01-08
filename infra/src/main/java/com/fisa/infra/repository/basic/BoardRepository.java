package com.fisa.infra.repository.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fisa.infra.domain.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>{

}
