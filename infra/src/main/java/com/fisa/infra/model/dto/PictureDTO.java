package com.fisa.infra.model.dto;

import java.time.LocalDateTime;

import com.fisa.infra.model.entity.Picture;

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
public class PictureDTO {

	//사진아이디
		@Id
		private String pictureId;
		
		//게시글아이디
		private String boardId;
		
		//사진파일주소
		private String pictureFileUrl;
		
		//등록일자
		private LocalDateTime uploadAt;
		
		//사진주소
		private String pictureUrl;
		
		public Picture toEntity() {
			return Picture.builder().pictureId(pictureId).boardId(boardId).pictureFileUrl(pictureFileUrl).uploadAt(uploadAt).pictureUrl(pictureUrl).build();
		}
}
