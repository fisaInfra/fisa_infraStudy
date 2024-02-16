package com.fisa.infra.board.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.fisa.infra.board.domain.dto.BoardRequestDTO;
import com.fisa.infra.board.domain.dto.UploadFile;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.board.repository.querydsl.QueryBoardRepository;
import com.fisa.infra.picture.domain.Picture;
import com.fisa.infra.picture.repository.CommonPictureRepository;
import com.fisa.infra.upload.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final AccountRepository accountRepository;
	private final QueryBoardRepository queryBoardRepository;
	private ModelMapper mapper = new ModelMapper();
	private final CommonPictureRepository commonPictureRepository;

	private final FileStore fileStore;

	@Transactional
	public Board writeBoard (String loginId, BoardDTO boardDTO) throws RuntimeException, IOException {
		Account account = accountRepository.findAccountByLoginId("s")
				.orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

		Board board = Board.builder()
				.account(account)
				.content(boardDTO.getContent())
				.title(boardDTO.getTitle())
				.build();

		//부모와 연관 관계
		board.addAccount(account);
		Board save = boardRepository.save(board);

		List<UploadFile> uploadFileList = fileStore.storeAllFile(boardDTO.getUploadFile());
		for (int i = 0; i < uploadFileList.size(); i++) {
			Picture picture = Picture.savePicture(uploadFileList.get(i).getStoreFileName(), uploadFileList.get(i).getUploadFileName());
			picture.addBoard(save);
			commonPictureRepository.save(picture);
		}
		return save;
	}
	@Transactional(readOnly = true)
	public List<BoardDTO> getAllBoard() {
		List<Board> boardAll = boardRepository.findAll();
		List<BoardDTO> collect = boardAll.stream().map(b -> mapper.map(b, BoardDTO.class)).collect(Collectors.toList());
		if(boardAll.isEmpty()){
			return Collections.emptyList();
		}
		return collect;
	}
	@Transactional(readOnly = true)
	public BoardDTO getBoardById (Long id){
		Optional<BoardDTO> board = queryBoardRepository.queryFindBoardById(id);
		return board.map(b -> mapper.map(b, BoardDTO.class)).orElse(null);
	}

	@Transactional
	public void updateBoardById(Long id, BoardRequestDTO boardRequestDTO) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("해당 ID에 해당하는 게시글을 찾을 수 없습니다."));

		board.updateBoard(boardRequestDTO.getTitle(), boardRequestDTO.getContent());
	}
}
