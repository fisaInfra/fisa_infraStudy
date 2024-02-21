package com.fisa.infra.comment.service;

import com.fisa.infra.account.domain.Account;
import com.fisa.infra.account.repository.jpa.AccountRepository;
import com.fisa.infra.board.domain.Board;
import com.fisa.infra.board.repository.jpa.BoardRepository;
import com.fisa.infra.comment.domain.Comment;
import com.fisa.infra.comment.domain.QComment;
import com.fisa.infra.comment.domain.dto.CommentDTO;
import com.fisa.infra.comment.repository.jpa.CommentRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AccountRepository accountRepository;
    private final BoardRepository boardRepository;
    private final JPAQueryFactory queryFactory;

    /**
     * 댓글 규칙
     * 1. 부모 - 자식 댓글이 존재할 경우
     *    부모 삭제 -> "해당 댓글은 삭제 되었습니다.
     *    자식 삭제 -> 바로 삭제
     *  부모는 자식이 없을 경우 삭제 처리
     *
     * 2. 부모 - 자식 댓글이 없을 경우
     *     바로 삭제
     */
    /**
     * 댓글 작성
     * @param commentDTO
     * @return 저장된 글
     */
    public Comment writeComment(CommentDTO commentDTO) throws RuntimeException {
        Account account = accountRepository.findAccountByLoginId(commentDTO.getLoginId())//commentDTO.getLoginId())
                .orElseThrow(() -> new RuntimeException("해당 로그인 아이디를 가진 회원이 존재하지 않습니다."));

        Board board = boardRepository.findById(commentDTO.getBoardId())//commentDTO.getBoardId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        /* QueryDSL 적용*/
        Comment parent = null;
        Comment comment = null;
        log.info("commentDTO.isParent(): {}", commentDTO.isParent());
        log.info("commentDTO.getParentId(): {}", commentDTO.getParentId());


        /**
         * 컬렉션으로 관리가 안됨.
         * */


        if(commentDTO.getParentId() != null) {
            QComment qComment = QComment.comment;

            parent = queryFactory
                    .select(qComment)
                    .from(qComment)
                    .where(qComment.commentId.eq(commentDTO.getParentId())
                            .and(qComment.parent.isNull())) // parent가 null인 경우에만 가져온다
                    .fetchOne();

            comment = Comment.builder()
                    .board(board)
                    .account(account)
                    .content(commentDTO.getContent())
                    .parent(parent)
                    .build();
        } else {
            comment = Comment.builder()
                    .board(board)
                    .account(account)
                    .content(commentDTO.getContent())
                    .build();
        }

        log.info("comment {}", comment);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     * */
    @Transactional
    public void deleteComment(Long commentId) {

        Comment comment = commentRepository.findByParent(commentId).orElseThrow(() -> new RuntimeException("해당 댓글은 존재하지 않습니다."));

        //부모 - 자식 댓글이 없을 경우 -> 자식 댓글
        if(comment.getChildren().isEmpty()){

            //자식 댓글 삭제
            commentRepository.deleteByComment(commentId);
            //부모 댓글이 삭제 처리 됐으면 자식 댓글 삭제 후, 부모 댓글도 삭제
            if(comment.getParent() != null){
                if(comment.isDeleted()){
                    commentRepository.deleteByComment(comment.getParent().getCommentId());
                }
            }
        } else { //부모 - 자식 댓글이 존재할 경우 - >부모 댓글
            comment.updateContent();
        }
    }

    /*
     * 댓글 조회
     * @param boardId
     * @return commentDTOList
     */
    public List<CommentDTO> readComment(Long boardId) throws RuntimeException {

        List<Comment> commentList = commentRepository.findCommentByBoardId(boardId);
        List<CommentDTO> commentDTOList = new ArrayList<>();

        /*
        * 댓글 하나씩 돌면서 commentId에 해당하는 parentId가 있는지 확인
        * */

        for (Comment comment : commentList) {
            //log.info("comment content : {}", comment.getContent());

            log.info("commentId : {}", comment.getCommentId());
            log.info("commentId : {}", comment.getParent());

            // 댓글일 때
            if(comment.getParent() == null) {
                log.info("getIsDeleted : {}", comment.getIsDeleted());


                // 댓글 세팅
                commentDTOList.add(
                        CommentDTO.builder()
                                .commentId(comment.getCommentId())
                                .boardId(comment.getBoard().getBoardId())
                                .accountId(comment.getAccount().getAccountId())
                                .imageUrl(comment.getAccount().getImageUrl())
                                .loginId(comment.getAccount().getLoginId())
                                .parentId(-1L)
                                .content(comment.getContent())
                                .createdAt(comment.getCreatedTime())
                                .updatedAt(comment.getModifiedTime())
                                .isParent(true)
                                .build()
                );

                // 대댓글 세팅
                List<Comment> childCommentList = commentRepository.findCommentByParentId(comment.getCommentId());

//                log.info("childCommentList : {}", childCommentList);

                for(Comment childComment : childCommentList) {
                    log.info("childCommentList : {}", childComment.getCommentId());
                    log.info("getIsDeleted : {}", comment.getIsDeleted());


                    commentDTOList.add(
                            CommentDTO.builder()
                                    .commentId(childComment.getCommentId())
                                    .boardId(childComment.getBoard().getBoardId())
                                    .accountId(childComment.getAccount().getAccountId())
                                    .imageUrl(childComment.getAccount().getImageUrl())
                                    .loginId(childComment.getAccount().getLoginId())
                                    .parentId(comment.getCommentId())
                                    .content(childComment.getContent())
                                    .createdAt(childComment.getCreatedTime())
                                    .updatedAt(childComment.getModifiedTime())
                                    .isParent(false)
                                    .build()
                    );
                }

                if(childCommentList != null) {
                    /**
                     * ????
                     * */
                }



            }








            // 댓글일 때
//            boolean isParent = true;
//            Long parentId = -1L; // 댓글의 parent id는 -1
//
//
//
//            // 대댓글일 때
//            if(comment.getParent() != null) {
//                isParent = false;
//                parentId = comment.getParent().getCommentId();
//            }

//            commentDTOList.add(
//                    CommentDTO.builder()
//                            .commentId(comment.getCommentId())
//                            .boardId(comment.getBoard().getBoardId())
//                            .accountId(comment.getAccount().getAccountId())
//                            .imageUrl(comment.getAccount().getImageUrl())
//                            .loginId(comment.getAccount().getLoginId())
//                            .isParent(isParent)
//                            .parentId(parentId)
//                            .content(comment.getContent())
//                            .createdAt(comment.getCreatedTime())
//                            .updatedAt(comment.getModifiedTime())
////                            .deleteYN(comment.getIsDeleted())
//                            .build()
//            );
        }
        return commentDTOList;
    }

}
