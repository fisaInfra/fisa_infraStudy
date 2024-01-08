package com.fisa.infra.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter   
@Setter  

@Entity
public class Board {

	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", content=" + content + ", uploadAt=" + uploadAt + ", title=" + title
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	//게시글아이디
	@Id
	private String boardId;
	
	//회원아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="accountId")
	private ArrayList<Account> account = new ArrayList<>();
	
	//내용
	private String content;

	//등록일자
	private LocalDateTime uploadAt;
	
	//제목
	private String title;
	
	//생성날짜
	private LocalDateTime createdAt;
	
	//수정날짜
	private LocalDateTime updatedAt;
}
