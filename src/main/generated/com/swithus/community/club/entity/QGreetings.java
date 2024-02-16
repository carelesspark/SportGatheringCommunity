package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGreetings is a Querydsl query type for Greetings
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGreetings extends EntityPathBase<Greetings> {

    private static final long serialVersionUID = 1461267358L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGreetings greetings = new QGreetings("greetings");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClubMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QGreetings(String variable) {
        this(Greetings.class, forVariable(variable), INITS);
    }

    public QGreetings(Path<? extends Greetings> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGreetings(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGreetings(PathMetadata metadata, PathInits inits) {
        this(Greetings.class, metadata, inits);
    }

    public QGreetings(Class<? extends Greetings> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QClubMember(forProperty("member"), inits.get("member")) : null;
    }

}

