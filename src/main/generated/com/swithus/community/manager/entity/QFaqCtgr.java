package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFaqCtgr is a Querydsl query type for FaqCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFaqCtgr extends EntityPathBase<FaqCtgr> {

    private static final long serialVersionUID = 160078577L;

    public static final QFaqCtgr faqCtgr = new QFaqCtgr("faqCtgr");

    public final StringPath ctgrType = createString("ctgrType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QFaqCtgr(String variable) {
        super(FaqCtgr.class, forVariable(variable));
    }

    public QFaqCtgr(Path<? extends FaqCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFaqCtgr(PathMetadata metadata) {
        super(FaqCtgr.class, metadata);
    }

}

