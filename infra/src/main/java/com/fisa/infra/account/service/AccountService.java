package com.fisa.infra.account.service;


import com.fisa.infra.account.domain.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.entity.Account;

import com.fisa.infra.account.domain.dto.AccountDTO;
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
		
//		accountRepository.findAccountByLoginId(accountdto.getLoginId()).orElseThrow();

		Account account = Account.builder()
				.loginId(accountdto.getLoginId()).pwd(accountdto.getPwd()).name(accountdto.getName()).belong(accountdto.getBelong())
				.gender(accountdto.isGender()).imageUrl(accountdto.getImageUrl()).stack(accountdto.getStack()).portfolio(accountdto.getPortfolio())
				.job(accountdto.getJob()).isDeleted(accountdto.isDeleted())
				.build();

		return accountRepository.save(account);
		
	}

		
	}

	public Account findAccountByLoginId(String loginId) throws RuntimeException{

		Account account = accountRepository.findAccountByLoginId(loginId)
				.orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));
		return account;

	}

	@Transactional
	public Account accountUpdate(AccountDTO accountDTO) throws RuntimeException{

		Account account = accountRepository.findAccountByLoginId(accountDTO.getLoginId())
				.orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));
		account.updateAccount(accountDTO);
		return account;

	}
	
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
