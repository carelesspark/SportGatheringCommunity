package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInquiryImg is a Querydsl query type for InquiryImg
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInquiryImg extends EntityPathBase<InquiryImg> {

    private static final long serialVersionUID = 614560566L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInquiryImg inquiryImg = new QInquiryImg("inquiryImg");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QInquiry inquiry;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uuid = createString("uuid");

    public QInquiryImg(String variable) {
        this(InquiryImg.class, forVariable(variable), INITS);
    }

    public QInquiryImg(Path<? extends InquiryImg> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInquiryImg(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInquiryImg(PathMetadata metadata, PathInits inits) {
        this(InquiryImg.class, metadata, inits);
    }

    public QInquiryImg(Class<? extends InquiryImg> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.inquiry = inits.isInitialized("inquiry") ? new QInquiry(forProperty("inquiry"), inits.get("inquiry")) : null;
    }

}

