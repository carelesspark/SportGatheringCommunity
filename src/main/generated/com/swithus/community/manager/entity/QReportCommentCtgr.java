package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportCommentCtgr is a Querydsl query type for ReportCommentCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportCommentCtgr extends EntityPathBase<ReportCommentCtgr> {

    private static final long serialVersionUID = -1001733850L;

    public static final QReportCommentCtgr reportCommentCtgr = new QReportCommentCtgr("reportCommentCtgr");

    public final StringPath ctgrType = createString("ctgrType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReportCommentCtgr(String variable) {
        super(ReportCommentCtgr.class, forVariable(variable));
    }

    public QReportCommentCtgr(Path<? extends ReportCommentCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportCommentCtgr(PathMetadata metadata) {
        super(ReportCommentCtgr.class, metadata);
    }

}

