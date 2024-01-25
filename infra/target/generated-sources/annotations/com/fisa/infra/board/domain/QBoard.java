package com.fisa.infra.board.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -1898997422L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final com.fisa.infra.common.domain.entity.QBaseEntity _super = new com.fisa.infra.common.domain.entity.QBaseEntity(this);

    public final com.fisa.infra.account.domain.QAccount account;

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final ListPath<com.fisa.infra.comment.domain.Comment, com.fisa.infra.comment.domain.QComment> commentList = this.<com.fisa.infra.comment.domain.Comment, com.fisa.infra.comment.domain.QComment>createList("commentList", com.fisa.infra.comment.domain.Comment.class, com.fisa.infra.comment.domain.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final ListPath<com.fisa.infra.picture.domain.Picture, com.fisa.infra.picture.domain.QPicture> pictureList = this.<com.fisa.infra.picture.domain.Picture, com.fisa.infra.picture.domain.QPicture>createList("pictureList", com.fisa.infra.picture.domain.Picture.class, com.fisa.infra.picture.domain.QPicture.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public final DatePath<java.sql.Date> updatedAt = createDate("updatedAt", java.sql.Date.class);

    public final DatePath<java.sql.Date> uploadAt = createDate("uploadAt", java.sql.Date.class);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.fisa.infra.account.domain.QAccount(forProperty("account")) : null;
    }

}

