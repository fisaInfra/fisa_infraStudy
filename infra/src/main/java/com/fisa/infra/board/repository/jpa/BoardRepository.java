package com.fisa.infra.board.repository.jpa;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.basic.CommonBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findAll();

    List<Board> findBoardByAccount(Account account);

    // AccountRepository
    Optional<Account> findAccountByLoginIdAndFetchBoards(String loginId);

    // BoardRepository
    List<Board> findBoardsByAccountId(Long accountId);
}
