package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubInquiry is a Querydsl query type for ClubInquiry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubInquiry extends EntityPathBase<ClubInquiry> {

    private static final long serialVersionUID = 450987893L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubInquiry clubInquiry = new QClubInquiry("clubInquiry");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    public final NumberPath<Byte> hasRead = createNumber("hasRead", Byte.class);

    public final QClubInquiryHeader header;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Byte> writer = createNumber("writer", Byte.class);

    public QClubInquiry(String variable) {
        this(ClubInquiry.class, forVariable(variable), INITS);
    }

    public QClubInquiry(Path<? extends ClubInquiry> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubInquiry(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubInquiry(PathMetadata metadata, PathInits inits) {
        this(ClubInquiry.class, metadata, inits);
    }

    public QClubInquiry(Class<? extends ClubInquiry> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.header = inits.isInitialized("header") ? new QClubInquiryHeader(forProperty("header"), inits.get("header")) : null;
    }

}

