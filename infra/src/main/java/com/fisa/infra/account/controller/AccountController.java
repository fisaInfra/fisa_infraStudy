package com.fisa.infra.account.controller;

import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
//	@PostMapping(value = "/create")
//	public ResponseEntity<?> accountCreate(Account account){
//		return ResponseEntity.ok(accountService.accountCreate(account));
//	}

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
