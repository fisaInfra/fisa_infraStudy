package com.fisa.infra.board.repository.querydsl;

import com.fisa.infra.account.domain.QAccount;
import com.fisa.infra.board.domain.QBoard;
import com.fisa.infra.board.domain.dto.BoardDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QueryBoardRepositoryImpl implements QueryBoardRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<BoardDTO> queryFindBoardById(Long id) {
        QBoard board = QBoard.board;
        QAccount account = QAccount.account;

        JPAQuery<BoardDTO> jpaQuery = queryFactory
                .select(Projections.constructor(BoardDTO.class,
                account.loginId,
                board.title,
                board.content,
                board.updatedAt,
                board.createdAt,
                board.updatedAt,
                board.isDeleted
        ))
                .from(board)
                .leftJoin(account)
                .on(board.account.accountId.eq(account.accountId))
                .where(board.boardId.eq(id));
        // JPQL 쿼리를 문자열로 얻어와서 로깅
        log.info("JPQL Query: {}", jpaQuery.toString());

        return Optional.ofNullable(jpaQuery.fetchOne());
    }
}
