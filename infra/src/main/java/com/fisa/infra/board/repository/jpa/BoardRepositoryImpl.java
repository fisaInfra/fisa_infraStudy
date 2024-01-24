package com.fisa.infra.board.repository.jpa;

/*
    QBoard는 Board 엔티티에 대한 QueryDSL 코드를 자동으로 생성한 클래스
    QBoard.board는 Board 엔티티에 대한 alias로 사용되어, board.boardId 등으로 필드에 접근할 수 있음
 */

import com.fisa.infra.board.domain.dto.BoardDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import static com.fisa.infra.account.domain.QAccount.account;
import static com.fisa.infra.board.domain.QBoard.board;

public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public BoardDTO findById(Long id) {

        return queryFactory
                .select(Projections.fields(BoardDTO.class,
                        board.boardId,
                        board.title,
                        board.content,
                        account.loginId.as("accountLoginId")
                ))
                .from(board)
                .leftJoin(board.account, account)
                .where(board.boardId.eq(id))
                .fetchOne();
    }
}
