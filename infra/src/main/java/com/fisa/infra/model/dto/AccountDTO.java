package com.fisa.infra.model.dto;

import java.time.LocalDateTime;

import com.fisa.infra.model.entity.Account;

import jakarta.persistence.Id;
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
public class AccountDTO {

	//id
		@Id
		private String accountId;
		
		//이름
		private String name;
		
		//소속
		private String belong;
		
		//성별
		private boolean gender;
		
		//회원사진주소
		private String image;
		
		//스택
		private String stack;
		
		//포트폴리오
		private String portfolio;
		
		//생성날짜
		private LocalDateTime createdAt;
		
		//수정날짜
		private LocalDateTime updatedAt;
		
		//역할
		private String role;
		
		//직군
		private String job;
		
		public Account toEntity() {
			return Account.builder().accountId(accountId).name(name).belong(belong).gender(gender).image(image).stack(stack).portfolio(portfolio).createdAt(createdAt).updatedAt(updatedAt).role(role).job(job).build();
		}
		
}


