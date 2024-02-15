package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportPost is a Querydsl query type for ReportPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportPost extends EntityPathBase<ReportPost> {

    private static final long serialVersionUID = -904365579L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportPost reportPost = new QReportPost("reportPost");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QReportPostCtgr ctgr;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final com.swithus.community.board.entity.QPromotion post;

    public final StringPath reason = createString("reason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportPost(String variable) {
        this(ReportPost.class, forVariable(variable), INITS);
    }

    public QReportPost(Path<? extends ReportPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportPost(PathMetadata metadata, PathInits inits) {
        this(ReportPost.class, metadata, inits);
    }

    public QReportPost(Class<? extends ReportPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ctgr = inits.isInitialized("ctgr") ? new QReportPostCtgr(forProperty("ctgr")) : null;
        this.post = inits.isInitialized("post") ? new com.swithus.community.board.entity.QPromotion(forProperty("post"), inits.get("post")) : null;
    }

}

