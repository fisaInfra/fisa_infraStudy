package com.fisa.infra.board.domain.dto;

import java.sql.Date;
import java.util.List;

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


	// account
	private String loginId;


	// board
	private String title;
	private String content;
	private Date uploadAt;
	private Date createdAt;
	private Date updatedAt;
	private boolean isDeleted;

	// picture
	private List<String> pictureUrl;


}
