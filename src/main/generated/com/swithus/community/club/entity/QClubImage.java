package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClubImage is a Querydsl query type for ClubImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClubImage extends EntityPathBase<ClubImage> {

    private static final long serialVersionUID = -576111991L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClubImage clubImage = new QClubImage("clubImage");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final QClub club;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath uuid = createString("uuid");

    public QClubImage(String variable) {
        this(ClubImage.class, forVariable(variable), INITS);
    }

    public QClubImage(Path<? extends ClubImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClubImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClubImage(PathMetadata metadata, PathInits inits) {
        this(ClubImage.class, metadata, inits);
    }

    public QClubImage(Class<? extends ClubImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.club = inits.isInitialized("club") ? new QClub(forProperty("club"), inits.get("club")) : null;
    }

}

