package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMeetingReply is a Querydsl query type for MeetingReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMeetingReply extends EntityPathBase<MeetingReply> {

    private static final long serialVersionUID = -1923233109L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMeetingReply meetingReply = new QMeetingReply("meetingReply");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMeeting meeting;

    public final QClubMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QMeetingReply(String variable) {
        this(MeetingReply.class, forVariable(variable), INITS);
    }

    public QMeetingReply(Path<? extends MeetingReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMeetingReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMeetingReply(PathMetadata metadata, PathInits inits) {
        this(MeetingReply.class, metadata, inits);
    }

    public QMeetingReply(Class<? extends MeetingReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.meeting = inits.isInitialized("meeting") ? new QMeeting(forProperty("meeting"), inits.get("meeting")) : null;
        this.member = inits.isInitialized("member") ? new QClubMember(forProperty("member"), inits.get("member")) : null;
    }

}

