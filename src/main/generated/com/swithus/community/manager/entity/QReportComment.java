package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportComment is a Querydsl query type for ReportComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportComment extends EntityPathBase<ReportComment> {

    private static final long serialVersionUID = 1716588778L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportComment reportComment = new QReportComment("reportComment");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QReportCommentCtgr ctgr_id;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath reason = createString("reason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportComment(String variable) {
        this(ReportComment.class, forVariable(variable), INITS);
    }

    public QReportComment(Path<? extends ReportComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportComment(PathMetadata metadata, PathInits inits) {
        this(ReportComment.class, metadata, inits);
    }

    public QReportComment(Class<? extends ReportComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ctgr_id = inits.isInitialized("ctgr_id") ? new QReportCommentCtgr(forProperty("ctgr_id")) : null;
    }

}
