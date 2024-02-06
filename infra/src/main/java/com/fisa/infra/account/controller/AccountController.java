package com.fisa.infra.account.controller;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.service.AccountService;
import com.fisa.infra.comment.domain.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	AccountService accountService;

	// http://localhost:3333/infra/api/accounts/create?loginId="asdf2"&pwd=111
	@PostMapping(value = "/create") //_role table  -- bullchallenger - customer 참
	public ResponseEntity<?> accountCreate(AccountDTO accountDTO){
		
		return ResponseEntity.ok(accountService.accountCreate(accountDTO));

	}

	// http://localhost:3333/infra/api/accounts/findById?loginId="asdf"
	@GetMapping("/findById")
	public ResponseEntity<?> accountFindById(String loginId){

		try {
			return ResponseEntity.ok(accountService.findAccountByLoginId(loginId));
		}
		catch (Exception e) {
			return ResponseEntity
					.internalServerError() // Error 500
					.body(e.getMessage());
		}

	}

	// http://localhost:3333/infra/api/accounts/update
	@PostMapping("/update")
	public ResponseEntity<?> accountUpdate(AccountDTO accountDTO){
		try {
			return ResponseEntity.ok(accountService.accountUpdate(accountDTO));
		}
		catch (Exception e) {
			return ResponseEntity
					.internalServerError() // Error 500
					.body(e.getMessage());
		}
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
