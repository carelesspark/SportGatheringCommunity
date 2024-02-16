package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubPostReply is a Querydsl query type for ClubPostReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubPostReply extends EntityPathBase<ClubPostReply> {

    private static final long serialVersionUID = -987023272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubPostReply clubPostReply = new QClubPostReply("clubPostReply");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QClubPost post;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QClubMember writer;

    public QClubPostReply(String variable) {
        this(ClubPostReply.class, forVariable(variable), INITS);
    }

    public QClubPostReply(Path<? extends ClubPostReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubPostReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubPostReply(PathMetadata metadata, PathInits inits) {
        this(ClubPostReply.class, metadata, inits);
    }

    public QClubPostReply(Class<? extends ClubPostReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QClubPost(forProperty("post"), inits.get("post")) : null;
        this.writer = inits.isInitialized("writer") ? new QClubMember(forProperty("writer"), inits.get("writer")) : null;
    }

}

