package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportPostCheck is a Querydsl query type for ReportPostCheck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportPostCheck extends EntityPathBase<ReportPostCheck> {

    private static final long serialVersionUID = -448828397L;

    public static final QReportPostCheck reportPostCheck = new QReportPostCheck("reportPostCheck");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isSolved = createBoolean("isSolved");

    public final BooleanPath isSuitabled = createBoolean("isSuitabled");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QReportPostCheck(String variable) {
        super(ReportPostCheck.class, forVariable(variable));
    }

    public QReportPostCheck(Path<? extends ReportPostCheck> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportPostCheck(PathMetadata metadata) {
        super(ReportPostCheck.class, metadata);
    }

}

