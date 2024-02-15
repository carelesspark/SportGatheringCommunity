package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGreetingsImage is a Querydsl query type for GreetingsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGreetingsImage extends EntityPathBase<GreetingsImage> {

    private static final long serialVersionUID = -1564532643L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGreetingsImage greetingsImage = new QGreetingsImage("greetingsImage");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QGreetings greetings;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uuid = createString("uuid");

    public QGreetingsImage(String variable) {
        this(GreetingsImage.class, forVariable(variable), INITS);
    }

    public QGreetingsImage(Path<? extends GreetingsImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGreetingsImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGreetingsImage(PathMetadata metadata, PathInits inits) {
        this(GreetingsImage.class, metadata, inits);
    }

    public QGreetingsImage(Class<? extends GreetingsImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.greetings = inits.isInitialized("greetings") ? new QGreetings(forProperty("greetings"), inits.get("greetings")) : null;
    }

}

