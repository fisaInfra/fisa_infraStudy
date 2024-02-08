package com.fisa.infra.account.controller;

import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountException;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/api")
public class AccountController {

 	private final AccountService accountService;

	@GetMapping("/account/create")
	public String createForm(Model model) {
		model.addAttribute("accountDTO", new AccountDTO());
		return "entire/account/AccountSaveForm";
	}

	@PostMapping(value = "/account/create")
	public String accountCreate(AccountDTO accountDTO) throws AccountException {
		accountService.accountCreate(accountDTO);
		return "redirect:/account/login";
	}

	//@sqldelete 바꿔서 지우는거 
	@DeleteMapping(value = "/delete1")
	public ResponseEntity deleteAccount1(String loginId) {
		accountService.accountDelete1(loginId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//account 에 deleteaccount 만들어서 지우는거
	@PostMapping(value = "/delete2")
	public ResponseEntity<AccountDTO> deleteAccount2(String loginId){
		return ResponseEntity.ok(accountService.accountDelete2(loginId));
	}
}
