package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCompetitionBoard is a Querydsl query type for CompetitionBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCompetitionBoard extends EntityPathBase<CompetitionBoard> {

    private static final long serialVersionUID = 1385971617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCompetitionBoard competitionBoard = new QCompetitionBoard("competitionBoard");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final com.swithus.community.global.entity.QSports sports;

    public final StringPath title = createString("title");

    public final com.swithus.community.user.entity.QUser user;

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QCompetitionBoard(String variable) {
        this(CompetitionBoard.class, forVariable(variable), INITS);
    }

    public QCompetitionBoard(Path<? extends CompetitionBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCompetitionBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCompetitionBoard(PathMetadata metadata, PathInits inits) {
        this(CompetitionBoard.class, metadata, inits);
    }

    public QCompetitionBoard(Class<? extends CompetitionBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.sports = inits.isInitialized("sports") ? new com.swithus.community.global.entity.QSports(forProperty("sports")) : null;
        this.user = inits.isInitialized("user") ? new com.swithus.community.user.entity.QUser(forProperty("user")) : null;
    }

}

