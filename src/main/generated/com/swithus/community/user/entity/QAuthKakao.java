package com.swithus.community.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthKakao is a Querydsl query type for AuthKakao
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthKakao extends EntityPathBase<AuthKakao> {

    private static final long serialVersionUID = -669069878L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthKakao authKakao = new QAuthKakao("authKakao");

    public final StringPath accessToken = createString("accessToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QAuthKakao(String variable) {
        this(AuthKakao.class, forVariable(variable), INITS);
    }

    public QAuthKakao(Path<? extends AuthKakao> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthKakao(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthKakao(PathMetadata metadata, PathInits inits) {
        this(AuthKakao.class, metadata, inits);
    }

    public QAuthKakao(Class<? extends AuthKakao> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

