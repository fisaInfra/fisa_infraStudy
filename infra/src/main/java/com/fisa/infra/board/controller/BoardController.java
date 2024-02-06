package com.fisa.infra.board.controller;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {
	
	private final BoardService boardService;

	@GetMapping("/board/create")
	public String createForm(Model model) {
		model.addAttribute("boardDTO", new BoardDTO());
		return "entire/board/boardSaveForm";
	}

	@PostMapping(value = "/board/create")
	public String writeBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO) throws IOException {
		// 게시글 작성자 정보 가져오기 (예시: 현재 인증된 사용자)
//	     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	     Account account = (Account) authentication.getPrincipal();
//	     String loginId = account.getLoginId();

//	    // 게시글 작성 서비스 호출
		boardService.writeBoard("김어진", boardDTO);
		return "redirect:/api/board/boardAll";
	}

	@GetMapping(value = "/board/boardAll")
	public String boardAll(Model model){
		List<BoardDTO> boardList = boardService.getAllBoard();
		model.addAttribute("boardList", boardList);
		return "entire/board/boardAllForm";
	}

	@GetMapping(value = "/boards")
	public String getBoardById(Model model) {
		//BoardDTO board = boardService.getBoardById(1602L);
		//model.addAttribute("board", board);
		return "entire/board/boardOneForm";
	}
}
