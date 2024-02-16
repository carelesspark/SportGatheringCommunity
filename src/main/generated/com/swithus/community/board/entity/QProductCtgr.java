package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductCtgr is a Querydsl query type for ProductCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductCtgr extends EntityPathBase<ProductCtgr> {

    private static final long serialVersionUID = -1520450767L;

    public static final QProductCtgr productCtgr = new QProductCtgr("productCtgr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QProductCtgr(String variable) {
        super(ProductCtgr.class, forVariable(variable));
    }

    public QProductCtgr(Path<? extends ProductCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductCtgr(PathMetadata metadata) {
        super(ProductCtgr.class, metadata);
    }

}

