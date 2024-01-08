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
public class Reply {
	
	//대댓글아이디
	@Id
	private String replyId;
	
	//댓글아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="commentId")
	private ArrayList<Comment> commentId = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Reply [content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", groupIndex="
				+ groupIndex + ", hierarchyIndex=" + hierarchyIndex + ", seqIndex=" + seqIndex + ", deleteYN="
				+ deleteYN + "]";
	}

	//게시글아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="boardId")
	private ArrayList<Board> boardId = new ArrayList<>(); 
	
	//회원아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="accountId")
	private ArrayList<Account> accountId = new ArrayList<>();
	
	//내용
	private String content;
	
	//생성날짜
	private LocalDateTime createdAt;
	
	//수정날짜
	private LocalDateTime updatedAt;
	
	//그릅
	private int groupIndex;
		
	//계층
	private int hierarchyIndex;
	
	//순서
	private int seqIndex;
	
	//삭제여부
	private boolean deleteYN;
		
		
		
}
