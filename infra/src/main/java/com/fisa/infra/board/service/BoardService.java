package com.fisa.infra.board.service;

import org.springframework.stereotype.Service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.jpa.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	 private final BoardRepository boardRepository;
	 
	 /**
	  * 게시글 작성
	  * @param boardDTO
	  * @return 저장된 글
	  */
//	 public Board writeBoard(BoardDTO boardDTO) throws RuntimeException {
//		 Account account = BoardRepository.findBoardByLoginId(boardDTO.getLoginId())
//	                .orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

	     
//	 }
}
