package com.fisa.infra.board.domain.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDTO {

	private String title;
	private String content;
	private List<MultipartFile> uploadFile;
}
