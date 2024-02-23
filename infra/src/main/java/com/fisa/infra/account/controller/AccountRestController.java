package com.fisa.infra.account.controller;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.service.AccountService;
import com.fisa.infra.board.domain.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AccountRestController {

    private final AccountService accountService;

	//mypage
	@GetMapping(value = "/accounts/findOne2/{id}")
	public ResponseEntity<AccountDTO> findAccountByLoginId2(@PathVariable String id) {
		AccountDTO accountDTO = accountService.findAccountByLoginId2(id);
		log.info("loginId {}", accountDTO.getLoginId());
		return ResponseEntity.ok(accountDTO);
	}

	// 게시글 찾는거
	@GetMapping("/accounts/findAll/{id}")
	public ResponseEntity<List<BoardDTO>> findAllBoard(@PathVariable String id) throws Exception {
		List<BoardDTO> boardAll = accountService.getAllBoard(id);
		return ResponseEntity.ok().body(boardAll);
	}


	//@sqldelete 바꿔서 지우는거
	@DeleteMapping(value = "/accounts/delete1")
	public ResponseEntity<AccountDTO> deleteAccount1(String loginId) {
		return ResponseEntity.ok(accountService.accountDelete1(loginId));
	}

    //    account 에 deleteaccount 만들어서 지우는거
    @PostMapping(value = "/accounts/delete2")
    public ResponseEntity<AccountDTO> deleteAccount2(String loginId) {
        return ResponseEntity.ok(accountService.accountDelete2(loginId));
    }

}
