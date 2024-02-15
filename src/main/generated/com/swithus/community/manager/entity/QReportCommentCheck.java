package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportCommentCheck is a Querydsl query type for ReportCommentCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportCommentCheck extends EntityPathBase<ReportCommentCheck> {

    private static final long serialVersionUID = -989338050L;

    public static final QReportCommentCheck reportCommentCheck = new QReportCommentCheck("reportCommentCheck");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath is_solved = createBoolean("is_solved");

    public final BooleanPath is_suitabled = createBoolean("is_suitabled");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportCommentCheck(String variable) {
        super(ReportCommentCheck.class, forVariable(variable));
    }

    public QReportCommentCheck(Path<? extends ReportCommentCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportCommentCheck(PathMetadata metadata) {
        super(ReportCommentCheck.class, metadata);
    }

}

