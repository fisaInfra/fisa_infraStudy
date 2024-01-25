package com.fisa.infra.role.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountRole is a Querydsl query type for AccountRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountRole extends EntityPathBase<AccountRole> {

    private static final long serialVersionUID = -1935986248L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountRole accountRole = new QAccountRole("accountRole");

    public final com.fisa.infra.account.domain.entity.QAccount account;

    public final QAccountRole_Pk pk;

    public final QRole role;

    public QAccountRole(String variable) {
        this(AccountRole.class, forVariable(variable), INITS);
    }

    public QAccountRole(Path<? extends AccountRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccountRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccountRole(PathMetadata metadata, PathInits inits) {
        this(AccountRole.class, metadata, inits);
    }

    public QAccountRole(Class<? extends AccountRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new com.fisa.infra.account.domain.entity.QAccount(forProperty("account")) : null;
        this.pk = inits.isInitialized("pk") ? new QAccountRole_Pk(forProperty("pk")) : null;
        this.role = inits.isInitialized("role") ? new QRole(forProperty("role")) : null;
    }

}

