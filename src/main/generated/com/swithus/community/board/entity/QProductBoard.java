package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductBoard is a Querydsl query type for ProductBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBoard extends EntityPathBase<ProductBoard> {

    private static final long serialVersionUID = 109588337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductBoard productBoard = new QProductBoard("productBoard");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final QProductCtgr ctgr;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final com.swithus.community.global.entity.QRegion region;

    public final com.swithus.community.global.entity.QSports sports;

    public final StringPath title = createString("title");

    public final com.swithus.community.user.entity.QUser user;

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QProductBoard(String variable) {
        this(ProductBoard.class, forVariable(variable), INITS);
    }

    public QProductBoard(Path<? extends ProductBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductBoard(PathMetadata metadata, PathInits inits) {
        this(ProductBoard.class, metadata, inits);
    }

    public QProductBoard(Class<? extends ProductBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ctgr = inits.isInitialized("ctgr") ? new QProductCtgr(forProperty("ctgr")) : null;
        this.region = inits.isInitialized("region") ? new com.swithus.community.global.entity.QRegion(forProperty("region")) : null;
        this.sports = inits.isInitialized("sports") ? new com.swithus.community.global.entity.QSports(forProperty("sports")) : null;
        this.user = inits.isInitialized("user") ? new com.swithus.community.user.entity.QUser(forProperty("user")) : null;
    }

}

