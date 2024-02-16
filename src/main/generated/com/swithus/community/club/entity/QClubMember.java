package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubMember is a Querydsl query type for ClubMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubMember extends EntityPathBase<ClubMember> {

    private static final long serialVersionUID = -572121300L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubMember clubMember = new QClubMember("clubMember");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath blacklistReason = createString("blacklistReason");

    public final QClub club;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isActive = createNumber("isActive", Byte.class);

    public final NumberPath<Byte> isBlacklist = createNumber("isBlacklist", Byte.class);

    public final com.swithus.community.user.entity.QUser member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Integer> rank = createNumber("rank", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QClubMember(String variable) {
        this(ClubMember.class, forVariable(variable), INITS);
    }

    public QClubMember(Path<? extends ClubMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubMember(PathMetadata metadata, PathInits inits) {
        this(ClubMember.class, metadata, inits);
    }

    public QClubMember(Class<? extends ClubMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new QClub(forProperty("club"), inits.get("club")) : null;
        this.member = inits.isInitialized("member") ? new com.swithus.community.user.entity.QUser(forProperty("member")) : null;
    }

}

