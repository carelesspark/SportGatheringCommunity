package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubPost is a Querydsl query type for ClubPost
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubPost extends EntityPathBase<ClubPost> {

    private static final long serialVersionUID = 1090005426L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubPost clubPost = new QClubPost("clubPost");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public final QClubMember writer;

    public QClubPost(String variable) {
        this(ClubPost.class, forVariable(variable), INITS);
    }

    public QClubPost(Path<? extends ClubPost> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubPost(PathMetadata metadata, PathInits inits) {
        this(ClubPost.class, metadata, inits);
    }

    public QClubPost(Class<? extends ClubPost> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.writer = inits.isInitialized("writer") ? new QClubMember(forProperty("writer"), inits.get("writer")) : null;
    }

}

