package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGreetingsLike is a Querydsl query type for GreetingsLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGreetingsLike extends EntityPathBase<GreetingsLike> {

    private static final long serialVersionUID = -1990045611L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGreetingsLike greetingsLike = new QGreetingsLike("greetingsLike");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QGreetings greetings;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClubMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QGreetingsLike(String variable) {
        this(GreetingsLike.class, forVariable(variable), INITS);
    }

    public QGreetingsLike(Path<? extends GreetingsLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGreetingsLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGreetingsLike(PathMetadata metadata, PathInits inits) {
        this(GreetingsLike.class, metadata, inits);
    }

    public QGreetingsLike(Class<? extends GreetingsLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.greetings = inits.isInitialized("greetings") ? new QGreetings(forProperty("greetings"), inits.get("greetings")) : null;
        this.member = inits.isInitialized("member") ? new QClubMember(forProperty("member"), inits.get("member")) : null;
    }

}

