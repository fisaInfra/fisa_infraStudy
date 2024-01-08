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
public class Picture {

	//사진아이디
	@Id
	private String pictureId;
	
	//게시글아이디
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="boardId")
	private ArrayList<Board> boardId = new ArrayList<>();
	
	@Override
	public String toString() {
		return "Picture [pictureFileUrl=" + pictureFileUrl + ", uploadAt=" + uploadAt + ", pictureUrl=" + pictureUrl
				+ "]";
	}

	//사진파일주소
	private String pictureFileUrl;
	
	//등록일자
	private LocalDateTime uploadAt;
	
	//사진주소
	private String pictureUrl;
	
}
