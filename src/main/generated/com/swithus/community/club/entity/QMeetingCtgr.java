package com.swithus.community.club.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMeetingCtgr is a Querydsl query type for MeetingCtgr
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMeetingCtgr extends EntityPathBase<MeetingCtgr> {

    private static final long serialVersionUID = -478114501L;

    public static final QMeetingCtgr meetingCtgr = new QMeetingCtgr("meetingCtgr");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QMeetingCtgr(String variable) {
        super(MeetingCtgr.class, forVariable(variable));
    }

    public QMeetingCtgr(Path<? extends MeetingCtgr> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMeetingCtgr(PathMetadata metadata) {
        super(MeetingCtgr.class, metadata);
    }

}

