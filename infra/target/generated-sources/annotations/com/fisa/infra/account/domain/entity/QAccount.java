package com.fisa.infra.account.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 821478781L;

    public static final QAccount account = new QAccount("account");

    public final com.fisa.infra.common.domain.entity.QBaseEntity _super = new com.fisa.infra.common.domain.entity.QBaseEntity(this);

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final StringPath belong = createString("belong");

    public final ListPath<com.fisa.infra.board.domain.Board, com.fisa.infra.board.domain.QBoard> board = this.<com.fisa.infra.board.domain.Board, com.fisa.infra.board.domain.QBoard>createList("board", com.fisa.infra.board.domain.Board.class, com.fisa.infra.board.domain.QBoard.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final BooleanPath gender = createBoolean("gender");

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final StringPath job = createString("job");

    public final StringPath loginId = createString("loginId");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final StringPath name = createString("name");

    public final StringPath portfolio = createString("portfolio");

    public final StringPath pwd = createString("pwd");

    public final StringPath stack = createString("stack");

    public QAccount(String variable) {
        super(Account.class, forVariable(variable));
    }

    public QAccount(Path<? extends Account> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount(PathMetadata metadata) {
        super(Account.class, metadata);
    }

}

