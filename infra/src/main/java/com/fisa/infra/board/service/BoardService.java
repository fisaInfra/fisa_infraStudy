package com.fisa.infra.board.service;

import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.board.repository.querydsl.QueryBoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	 private final BoardRepository boardRepository;
	 private final AccountRepository accountRepository;
	 private final QueryBoardRepository queryBoardRepository;
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

		 /*
		 * 게시글에 들어갈 사진도 저장해야 하니깐 로직 추가해주새요
		 * */

	     return boardRepository.save(board);
	 }

	public List<BoardDTO> getAllBoard() {
		List<Board> boardAll = boardRepository.findAll();
		List<BoardDTO> collect = boardAll.stream().map(b -> mapper.map(b, BoardDTO.class)).collect(Collectors.toList());
		log.info("{} ", boardAll.size());

		if(boardAll.isEmpty()){
			return Collections.emptyList();
		}

		return collect;
	}

    public BoardDTO getBoardById(Long id) {
		 Optional<BoardDTO> board = queryBoardRepository.queryFindBoardById(id);

		 return board.map(b -> mapper.map(b, BoardDTO.class))
				.orElse(null);
    }


}
