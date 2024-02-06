package com.fisa.infra.role.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccountRole_Pk is a Querydsl query type for Pk
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAccountRole_Pk extends BeanPath<AccountRole.Pk> {

    private static final long serialVersionUID = -2145416687L;

    public static final QAccountRole_Pk pk = new QAccountRole_Pk("pk");

    public final NumberPath<Long> accountId = createNumber("accountId", Long.class);

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public QAccountRole_Pk(String variable) {
        super(AccountRole.Pk.class, forVariable(variable));
    }

    public QAccountRole_Pk(Path<? extends AccountRole.Pk> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccountRole_Pk(PathMetadata metadata) {
        super(AccountRole.Pk.class, metadata);
    }

}

