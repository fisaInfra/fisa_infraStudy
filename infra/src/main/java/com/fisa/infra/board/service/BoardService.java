package com.fisa.infra.board.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	 private final BoardRepository boardRepository;
	 private final AccountRepository accountRepository;
	 private ModelMapper mapper = new ModelMapper();

	 /**
	  * 게시글 작성
	  * @param boardDTO
	  * @return 저장된 글
	  */
	 public Board writeBoard(BoardDTO boardDTO) throws RuntimeException {
		 Account account = accountRepository.findAccountByLoginId(boardDTO.getLoginId())
	                .orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

	     Board board = Board.builder()
	    		 .account(account)
	    		 .content(boardDTO.getContent())
	    		 .title(boardDTO.getTitle())
	    		 .build();

	     return boardRepository.save(board);
	 }

	public List<BoardDTO> getAllBoard() {
		List<Board> boardAll = boardRepository.findAll();

		if(boardAll.isEmpty()){
			return Collections.emptyList();
		}

		return Arrays.asList(mapper.map(boardAll, BoardDTO.class));
	}

    public BoardDTO getBoardById(Long id) {
		 Optional<Board> board = boardRepository.findById(id);

		 return board.map(b -> mapper.map(b, BoardDTO.class))
				.orElse(null);
    }
}
