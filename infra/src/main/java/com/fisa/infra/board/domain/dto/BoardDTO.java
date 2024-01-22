package com.fisa.infra.board.domain.dto;

import java.sql.Date;

import com.fisa.infra.account.domain.Account;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardDTO {
	
	//회원 아이디
	private String loginId;
	
	//내용
	private String content;

	//제목
	private String title;
	
	//등록일자
	private Date uploadAt;
	
	//생성날짜
	private Date createdAt;
	
	//수정날짜
	private Date updatedAt;
	
	//삭제여부
	private boolean isDeleted;
		
}
