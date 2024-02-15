package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotionReply is a Querydsl query type for PromotionReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotionReply extends EntityPathBase<PromotionReply> {

    private static final long serialVersionUID = -1286296127L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotionReply promotionReply = new QPromotionReply("promotionReply");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QPromotion promotion;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final com.swithus.community.user.entity.QUser user;

    public QPromotionReply(String variable) {
        this(PromotionReply.class, forVariable(variable), INITS);
    }

    public QPromotionReply(Path<? extends PromotionReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPromotionReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPromotionReply(PathMetadata metadata, PathInits inits) {
        this(PromotionReply.class, metadata, inits);
    }

    public QPromotionReply(Class<? extends PromotionReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.promotion = inits.isInitialized("promotion") ? new QPromotion(forProperty("promotion"), inits.get("promotion")) : null;
        this.user = inits.isInitialized("user") ? new com.swithus.community.user.entity.QUser(forProperty("user")) : null;
    }

}

