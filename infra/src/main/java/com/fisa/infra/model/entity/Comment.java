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
@Builder

@Entity
public class Comment {
	
	//댓글아이디
	@Id
	private String commentId;
	
	//게시글아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="accountId")
	private ArrayList<Board> boardId = new ArrayList<>(); 
	
	//회원아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="accountId")
	private ArrayList<Account> accountId;
	
	@Override
	public String toString() {
		return "Comment [content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleteYN="
				+ deleteYN + ", groupIndex=" + groupIndex + ", hierarchyIndex=" + hierarchyIndex + ", seqIndex="
				+ seqIndex + "]";
	}

	//내용
	private String content;
	
	//생성날짜
	private LocalDateTime createdAt;
	
	//수정날짜
	private LocalDateTime updatedAt;
	
	//삭제여부
	private boolean deleteYN;
	
	//그릅
	private int groupIndex;
	
	//계층
	private int hierarchyIndex;
	
	//순서
	private int seqIndex;
}
