package com.fisa.infra.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.repository.jpa.AccountRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

	// 이거도 하나더 만들고 이걸 implements로 사용??
	@Autowired
	private AccountRepository accountRepository;
	
	public AccountDTO accountCreate(Account account) {
		
		accountRepository.findAccountByLoginId(account.getLoginId()).orElseThrow();
		
		accountRepository.save(account);
		
		return AccountDTO.builder().accountId(account.getAccountId())
				.loginId(account.getLoginId()).pwd(account.getPwd()).name(account.getName()).belong(account.getBelong())
				.gender(account.isGender()).imageUrl(account.getImageUrl()).stack(account.getStack()).portfolio(account.getPortfolio())
				.job(account.getJob()).isDeleted(account.isDeleted())
				.build();
		
	}
	
	//테스트
//	public AccountDTO accountCreateTest(String loginId) {
//
////		accountRepository.findAccountByLoginId(loginId).orElseThrow();
//
//		Account account = Account.createAccountTest(loginId);
//
//		accountRepository.save(account);
//
//		return AccountDTO.builder().accountId(account.getAccountId())
//				.loginId(account.getLoginId()).pwd("1234")
//				.build();
//
//	}

	
	//sql 써보는거
	public void accountDelete1(String loginId) {
		accountRepository.deleteAccountByLoginId(loginId);
	}
	
	//DTO에서 isdeleted 바꿔서 지우는거 
	public AccountDTO accountDelete2(String loginId) {
	
		Account account = accountRepository.findAccountByLoginId(loginId).orElseThrow();
	
		Account deletedAccount = account.deletedAccount(account.getLoginId()); 
	
		return AccountDTO.fromEntity(deletedAccount);
	}
	
}
