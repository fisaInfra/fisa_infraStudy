package com.fisa.infra.board.controller;


import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fisa.infra.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

}
