package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportPostCtgr is a Querydsl query type for ReportPostCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportPostCtgr extends EntityPathBase<ReportPostCtgr> {

    private static final long serialVersionUID = -1261392719L;

    public static final QReportPostCtgr reportPostCtgr = new QReportPostCtgr("reportPostCtgr");

    public final StringPath ctgr_type = createString("ctgr_type");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QReportPostCtgr(String variable) {
        super(ReportPostCtgr.class, forVariable(variable));
    }

    public QReportPostCtgr(Path<? extends ReportPostCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportPostCtgr(PathMetadata metadata) {
        super(ReportPostCtgr.class, metadata);
    }

}

