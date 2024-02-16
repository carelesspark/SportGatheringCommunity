package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubInquiryHeader is a Querydsl query type for ClubInquiryHeader
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubInquiryHeader extends EntityPathBase<ClubInquiryHeader> {

    private static final long serialVersionUID = -986502558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubInquiryHeader clubInquiryHeader = new QClubInquiryHeader("clubInquiryHeader");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QClub club;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isActive = createNumber("isActive", Byte.class);

    public final NumberPath<Byte> lastSender = createNumber("lastSender", Byte.class);

    public final DateTimePath<java.time.LocalDateTime> lastSendTime = createDateTime("lastSendTime", java.time.LocalDateTime.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final com.swithus.community.user.entity.QUser user;

    public QClubInquiryHeader(String variable) {
        this(ClubInquiryHeader.class, forVariable(variable), INITS);
    }

    public QClubInquiryHeader(Path<? extends ClubInquiryHeader> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubInquiryHeader(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubInquiryHeader(PathMetadata metadata, PathInits inits) {
        this(ClubInquiryHeader.class, metadata, inits);
    }

    public QClubInquiryHeader(Class<? extends ClubInquiryHeader> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new QClub(forProperty("club"), inits.get("club")) : null;
        this.user = inits.isInitialized("user") ? new com.swithus.community.user.entity.QUser(forProperty("user")) : null;
    }

}

