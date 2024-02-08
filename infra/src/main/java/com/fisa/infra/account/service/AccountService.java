package com.fisa.infra.account.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.account.repository.querydsl.QueryAccountRepository;
import com.fisa.infra.common.exception.ErrorCode;
import com.fisa.infra.role.domain.entity.Role;
import com.fisa.infra.role.domain.entity.AccountRole;
import com.fisa.infra.role.repository.jpa.AccountRoleRepository;
import com.fisa.infra.role.repository.querydsl.RoleRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	private final RoleRepositoryImpl roleRepository;
	private final AccountRoleRepository accountRoleRepository;
	private final QueryAccountRepository queryAccountRepository;

	private ModelMapper mapper = new ModelMapper();

	public Account accountCreate(AccountDTO accountdto) throws AccountException {
		
		accountRepository.findAccountByLoginId(accountdto.getLoginId()).ifPresent(
				existingAccount -> {

                }
		);

		Account account = Account.builder()
				.loginId(accountdto.getLoginId()).pwd(accountdto.getPwd()).name(accountdto.getName()).belong(accountdto.getBelong())
				.gender(accountdto.isGender()).imageUrl(accountdto.getImageUrl()).stack(accountdto.getStack()).portfolio(accountdto.getPortfolio())
				.job(accountdto.getJob()).isDeleted(accountdto.isDeleted())
				.build();


		accountRepository.save(account);

		// 1번 ROLE_ADMIN , 2번 ROLE_USER
		Optional<Role> op = roleRepository.findById((long)2);

		if (op.isEmpty()) {
			throw new AccountException(ErrorCode.NOT_FOUND_ROLE);
		}

		Role role = op.get();

		AccountRole accountRole = AccountRole.createAccountRole(account, role);

		accountRoleRepository.save(accountRole);

		return account;

	}

	public AccountDTO getAccountByLoginId (String id){

		Optional<AccountDTO> account = queryAccountRepository.queryFindAccountByLoginId(id);

		return account.map(b -> mapper.map(b, AccountDTO.class)).orElse(null);
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
