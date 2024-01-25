package com.fisa.infra.picture.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPicture is a Querydsl query type for Picture
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPicture extends EntityPathBase<Picture> {

    private static final long serialVersionUID = -2056599598L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPicture picture = new QPicture("picture");

    public final com.fisa.infra.board.domain.QBoard board;

    public final StringPath pictureFileUrl = createString("pictureFileUrl");

    public final NumberPath<Long> pictureId = createNumber("pictureId", Long.class);

    public final StringPath pictureUrl = createString("pictureUrl");

    public QPicture(String variable) {
        this(Picture.class, forVariable(variable), INITS);
    }

    public QPicture(Path<? extends Picture> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPicture(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPicture(PathMetadata metadata, PathInits inits) {
        this(Picture.class, metadata, inits);
    }

    public QPicture(Class<? extends Picture> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.fisa.infra.board.domain.QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

