package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportCommentCheck is a Querydsl query type for ReportCommentCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportCommentCheck extends EntityPathBase<ReportCommentCheck> {

    private static final long serialVersionUID = -989338050L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportCommentCheck reportCommentCheck = new QReportCommentCheck("reportCommentCheck");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QReportComment comment;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSolved = createBoolean("isSolved");

    public final BooleanPath isSuitabled = createBoolean("isSuitabled");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportCommentCheck(String variable) {
        this(ReportCommentCheck.class, forVariable(variable), INITS);
    }

    public QReportCommentCheck(Path<? extends ReportCommentCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportCommentCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportCommentCheck(PathMetadata metadata, PathInits inits) {
        this(ReportCommentCheck.class, metadata, inits);
    }

    public QReportCommentCheck(Class<? extends ReportCommentCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment = inits.isInitialized("comment") ? new QReportComment(forProperty("comment"), inits.get("comment")) : null;
    }

}

