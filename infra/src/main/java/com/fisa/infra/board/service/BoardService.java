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
		Account account = accountRepository.findAccountByLoginId("onionhaseyo")
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
	
	/* 게시글 삭제
	 * 게시글에 달린 댓글은 cascade로 entity 딴에서 삭제 처리
	 */
	@Transactional
	public void deleteBoard(Long boardId, String loginId) {
	    // 게시글 조회
	    Board board = boardRepository.findById(boardId)
	            .orElseThrow(() -> new RuntimeException("해당 게시글은 존재하지 않습니다."));
	    
	    // loginId로 account_id 찾기
        Account account = accountRepository.findAccountByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("해당하는 로그인 ID의 계정이 없습니다."));
        
        // 게시글의 account_id와 찾은 account_id 비교 후 삭제
        if (board.getAccount().getAccountId().equals(account.getAccountId())) {
            boardRepository.deleteById(boardId);
        } else {
            throw new RuntimeException("해당 계정으로는 삭제할 수 없는 게시글입니다.");
        }
	}

	public List<String> getBoardPictures(Long id) {
		Board board = boardRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("해당 ID에 해당하는 게시글을 찾을 수 없습니다."));

		// 해당 게시글에 첨부된 사진들의 URL 리스트를 추출
		List<String> pictureUrls = board.getPictureList().stream()
				.map(Picture::getPictureUrl) // Picture 엔티티에서 사진의 URL을 가져오는 메소드를 호출
				.collect(Collectors.toList());

		return pictureUrls;
	}
}
