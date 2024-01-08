package com.fisa.infra.model.dto;

import java.time.LocalDateTime;

import com.fisa.infra.model.entity.Board;

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
public class BoardDTO {
	//게시글아이디
		@Id
		private String boardId;
		
		//회원아이디
		private String accountId;
		
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
		
		public Board toEntity() {
			return Board.builder().boardId(boardId).accountId(accountId).content(content).uploadAt(uploadAt).title(title).createdAt(createdAt).updatedAt(updatedAt).build();
		}
}
