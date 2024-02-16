package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubPostLike is a Querydsl query type for ClubPostLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubPostLike extends EntityPathBase<ClubPostLike> {

    private static final long serialVersionUID = 1353458793L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubPostLike clubPostLike = new QClubPostLike("clubPostLike");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClubMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QClubPost post;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QClubPostLike(String variable) {
        this(ClubPostLike.class, forVariable(variable), INITS);
    }

    public QClubPostLike(Path<? extends ClubPostLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubPostLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubPostLike(PathMetadata metadata, PathInits inits) {
        this(ClubPostLike.class, metadata, inits);
    }

    public QClubPostLike(Class<? extends ClubPostLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QClubMember(forProperty("member"), inits.get("member")) : null;
        this.post = inits.isInitialized("post") ? new QClubPost(forProperty("post"), inits.get("post")) : null;
    }

}

