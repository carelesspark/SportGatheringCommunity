package com.swithus.community.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthNaver is a Querydsl query type for AuthNaver
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthNaver extends EntityPathBase<AuthNaver> {

    private static final long serialVersionUID = -666288617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthNaver authNaver = new QAuthNaver("authNaver");

    public final StringPath accessToken = createString("accessToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QUser user;

    public QAuthNaver(String variable) {
        this(AuthNaver.class, forVariable(variable), INITS);
    }

    public QAuthNaver(Path<? extends AuthNaver> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthNaver(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthNaver(PathMetadata metadata, PathInits inits) {
        this(AuthNaver.class, metadata, inits);
    }

    public QAuthNaver(Class<? extends AuthNaver> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

