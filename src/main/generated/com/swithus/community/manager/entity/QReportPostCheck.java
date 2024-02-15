package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReportPostCheck is a Querydsl query type for ReportPostCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportPostCheck extends EntityPathBase<ReportPostCheck> {

    private static final long serialVersionUID = -448828397L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReportPostCheck reportPostCheck = new QReportPostCheck("reportPostCheck");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSolved = createBoolean("isSolved");

    public final BooleanPath isSuitabled = createBoolean("isSuitabled");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QReportPost post;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportPostCheck(String variable) {
        this(ReportPostCheck.class, forVariable(variable), INITS);
    }

    public QReportPostCheck(Path<? extends ReportPostCheck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReportPostCheck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReportPostCheck(PathMetadata metadata, PathInits inits) {
        this(ReportPostCheck.class, metadata, inits);
    }

    public QReportPostCheck(Class<? extends ReportPostCheck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QReportPost(forProperty("post"), inits.get("post")) : null;
    }

}

