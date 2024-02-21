package com.fisa.infra.account.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.domain.dto.AccountDTO;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.account.repository.querydsl.QueryAccountRepositoryImpl;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.common.exception.ClientException;
import com.fisa.infra.common.exception.ErrorCode;
import com.fisa.infra.role.domain.entity.AccountRole;
import com.fisa.infra.role.domain.entity.Role;
import com.fisa.infra.role.repository.jpa.AccountRoleRepository;
import com.fisa.infra.role.repository.querydsl.RoleRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	private final RoleRepositoryImpl roleRepository;
	private final AccountRoleRepository accountRoleRepository;
	private final BoardRepository boardRepository;
	private final QueryAccountRepositoryImpl queryAccountRepositoryImpl;

	private ModelMapper mapper = new ModelMapper();
	private final PasswordEncoder passwordEncoder;


	public Account accountCreate(AccountDTO accountdto) throws AccountException {
		
		accountRepository.findAccountByLoginId(accountdto.getLoginId()).ifPresent(
				existingAccount -> {

                }
		);

		Account account = Account.builder()
				.loginId(accountdto.getLoginId()).pwd(passwordEncoder.encode(accountdto.getPwd())).name(accountdto.getName()).belong(accountdto.getBelong())
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




	//querydsl
	public AccountDTO findAccountByLoginId (String id){

		Optional<AccountDTO> account = queryAccountRepositoryImpl.queryFindAccountByLoginId(id);
		log.info("service return : " + account);
		return account.map(b -> mapper.map(b, AccountDTO.class)).orElse(null);
	}

	public AccountDTO findAccountByLoginId2 (String id){

		Optional<Account> account2b = accountRepository.findAccountByLoginId(id);
		log.info(account2b.get().getLoginId());

		Account account = accountRepository.findAccountByLoginId(id)
				.orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));
		log.info("service return : " + account);
		return AccountDTO.fromEntity(account);

	}

	public List<BoardDTO> getAllBoard(String id) throws ClientException {

		Account account = accountRepository.findAccountByLoginId(id)
				.orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));


		/**
		 * 조인이 없네.
		 * */
		List<Board> boardAll = boardRepository.findBoardByAccount(account);
		List<BoardDTO> boardDTOAll = Arrays.asList(mapper.map(boardAll, BoardDTO[].class));
		return boardDTOAll;
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
