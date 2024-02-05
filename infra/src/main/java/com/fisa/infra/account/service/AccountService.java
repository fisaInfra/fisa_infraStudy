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

	/**
	 * 회원을 가입하기 때문에 유저 정보가 없음.
	 * accountRepository.findAccountByLoginId(accountDTO.getLoginId()).orElseThrow(); -> 쓸모 없는 코드
	 * Account는 엔티티고 db에서는 테이블인데 파라미터로 받으면 객체 생성은 어떻게 할 것인지 다시 생각하기.
	 * dto로 받고 엔티티로 저장하고 디비에 넣어야지 순서가 완전 뒤죽박죽임
	 * */
	
//	public AccountDTO accountCreate(Account account) {
//
//		accountRepository.findAccountByLoginId(account.getLoginId()).orElseThrow();
//
//		accountRepository.save(account);
//
//		return AccountDTO.builder().accountId(account.getAccountId())
//				.loginId(account.getLoginId()).pwd(account.getPwd()).name(account.getName()).belong(account.getBelong())
//				.gender(account.isGender()).imageUrl(account.getImageUrl()).stack(account.getStack()).portfolio(account.getPortfolio())
//				.job(account.getJob()).isDeleted(account.isDeleted())
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
