package com.swithus.community.global.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSports is a Querydsl query type for Sports
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSports extends EntityPathBase<Sports> {

    private static final long serialVersionUID = -2061054040L;

    public static final QSports sports = new QSports("sports");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QSports(String variable) {
        super(Sports.class, forVariable(variable));
    }

    public QSports(Path<? extends Sports> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSports(PathMetadata metadata) {
        super(Sports.class, metadata);
    }

}

