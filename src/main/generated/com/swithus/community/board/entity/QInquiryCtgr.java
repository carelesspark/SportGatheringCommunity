package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInquiryCtgr is a Querydsl query type for InquiryCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryCtgr extends EntityPathBase<InquiryCtgr> {

    private static final long serialVersionUID = 1871336457L;

    public static final QInquiryCtgr inquiryCtgr = new QInquiryCtgr("inquiryCtgr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QInquiryCtgr(String variable) {
        super(InquiryCtgr.class, forVariable(variable));
    }

    public QInquiryCtgr(Path<? extends InquiryCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInquiryCtgr(PathMetadata metadata) {
        super(InquiryCtgr.class, metadata);
    }

}

