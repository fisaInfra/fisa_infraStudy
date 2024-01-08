package com.fisa.infra.domain.dto;

import java.time.LocalDateTime;

import com.fisa.infra.domain.entity.Comment;

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
public class CommentDTO {
	//댓글아이디
		@Id
		private String commentId;
		
		//게시글아이디
		private String boardId; 
		
		//회원아이디
		private String accountId;
		
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
