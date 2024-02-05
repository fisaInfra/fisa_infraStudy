package com.fisa.infra.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.domain.entity.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account accountCreate(AccountDTO accountdto) {
		
		accountRepository.findAccountByLoginId(accountdto.getLoginId()).orElseThrow();

		Account account = Account.builder().accountId(accountdto.getAccountId())
				.loginId(accountdto.getLoginId()).pwd(accountdto.getPwd()).name(accountdto.getName()).belong(accountdto.getBelong())
				.gender(accountdto.isGender()).imageUrl(accountdto.getImageUrl()).stack(accountdto.getStack()).portfolio(accountdto.getPortfolio())
				.job(accountdto.getJob()).isDeleted(accountdto.isDeleted())
				.build();

		return accountRepository.save(account);
		

		
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
