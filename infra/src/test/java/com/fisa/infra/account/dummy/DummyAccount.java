package com.fisa.infra.account.dummy;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.board.domain.Board;

import java.util.List;

public class DummyAccount {


	public static Account dummy(Board board){
		return Account.builder()
			.loginId("testId")
			.pwd("test")
			.name("test")
			.belong("클라우드 엔지니어링")
			.gender(true)
			.imageUrl("testUrl")
			.stack("dd")
			.portfolio("dd")
			.job("?")
			.board(List.of(board))
			.build();
	}
}
