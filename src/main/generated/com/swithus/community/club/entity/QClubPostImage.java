package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubPostImage is a Querydsl query type for ClubPostImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubPostImage extends EntityPathBase<ClubPostImage> {

    private static final long serialVersionUID = -995111223L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubPostImage clubPostImage = new QClubPostImage("clubPostImage");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final QClubPost post;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uuid = createString("uuid");

    public QClubPostImage(String variable) {
        this(ClubPostImage.class, forVariable(variable), INITS);
    }

    public QClubPostImage(Path<? extends ClubPostImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubPostImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubPostImage(PathMetadata metadata, PathInits inits) {
        this(ClubPostImage.class, metadata, inits);
    }

    public QClubPostImage(Class<? extends ClubPostImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QClubPost(forProperty("post"), inits.get("post")) : null;
    }

}

