package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = -1286392151L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final com.swithus.community.club.entity.QClub club;

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QPromotion(String variable) {
        this(Promotion.class, forVariable(variable), INITS);
    }

    public QPromotion(Path<? extends Promotion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPromotion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPromotion(PathMetadata metadata, PathInits inits) {
        this(Promotion.class, metadata, inits);
    }

    public QPromotion(Class<? extends Promotion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new com.swithus.community.club.entity.QClub(forProperty("club"), inits.get("club")) : null;
    }

}

