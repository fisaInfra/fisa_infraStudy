package com.fisa.infra.comment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = 1874573746L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final com.fisa.infra.common.domain.entity.QBaseEntity _super = new com.fisa.infra.common.domain.entity.QBaseEntity(this);

    public final com.fisa.infra.account.domain.QAccount account;

    public final com.fisa.infra.board.domain.QBoard board;

    public final ListPath<Comment, QComment> children = this.<Comment, QComment>createList("children", Comment.class, QComment.class, PathInits.DIRECT2);

    public final NumberPath<Long> commentId = createNumber("commentId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final NumberPath<Integer> level = createNumber("level", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedTime = _super.modifiedTime;

    public final QComment parent;

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.fisa.infra.account.domain.QAccount(forProperty("account")) : null;
        this.board = inits.isInitialized("board") ? new com.fisa.infra.board.domain.QBoard(forProperty("board"), inits.get("board")) : null;
        this.parent = inits.isInitialized("parent") ? new QComment(forProperty("parent"), inits.get("parent")) : null;
    }

}

