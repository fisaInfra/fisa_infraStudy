package com.fisa.infra.board.controller;


import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.fisa.infra.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	/**
	 * 게시글 작성
	 * @param boardDTO
	 * @return ResponseEntity
	 */
	

	@PostMapping(value = "/createBoard")
	public ResponseEntity<?> writeBoard(@RequestBody BoardDTO boardDTO){
		 try {
	            // 게시글 작성자 정보 가져오기 (예시: 현재 인증된 사용자)
//	            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	            Account account = (Account) authentication.getPrincipal();
//	            String loginId = account.getLoginId(); // 현재 사용자의 로그인 아이디 또는 식별자

	            // 게시글 작성 서비스 호출
	            Board board = boardService.writeBoard(boardDTO);

	            // 성공적인 응답
	            return ResponseEntity.ok(board);
	        } catch (Exception e) {
	            // 에러 응답
	            log.error("Error while writing board", e);
	            return ResponseEntity
	                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("An error occurred while processing the request.");
	        }
	}

	@GetMapping(value = "/boardAll")
	public ResponseEntity<List<BoardDTO>> boardAll(){
		try{
			List<BoardDTO> boardList = boardService.getAllBoard();
			return ResponseEntity.ok(boardList);
		}catch (Exception e) {
			// 에러 응답
			log.error("Error while get board list", e);
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.emptyList());
		}
	}


	@GetMapping(value = "/boards/{id}")
	public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
		try {
			BoardDTO board = boardService.getBoardById(id);

			if (board != null) {
				return ResponseEntity.ok(board);
			} else {
				// 게시글이 없을 경우 NOT_FOUND 상태 코드 반환
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			// 에러 응답
			log.error("Error while get board by ID", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
