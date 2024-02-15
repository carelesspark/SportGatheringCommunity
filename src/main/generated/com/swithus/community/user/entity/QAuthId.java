package com.swithus.community.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAuthId is a Querydsl query type for AuthId
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAuthId extends EntityPathBase<AuthId> {

    private static final long serialVersionUID = -187875980L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAuthId authId = new QAuthId("authId");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QUser user;

    public final StringPath userId = createString("userId");

    public final StringPath userPwd = createString("userPwd");

    public QAuthId(String variable) {
        this(AuthId.class, forVariable(variable), INITS);
    }

    public QAuthId(Path<? extends AuthId> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAuthId(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAuthId(PathMetadata metadata, PathInits inits) {
        this(AuthId.class, metadata, inits);
    }

    public QAuthId(Class<? extends AuthId> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

