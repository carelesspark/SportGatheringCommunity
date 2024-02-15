package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMainImage is a Querydsl query type for MainImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMainImage extends EntityPathBase<MainImage> {

    private static final long serialVersionUID = 2130823425L;

    public static final QMainImage mainImage = new QMainImage("mainImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath path = createString("path");

    public final StringPath uuid = createString("uuid");

    public QMainImage(String variable) {
        super(MainImage.class, forVariable(variable));
    }

    public QMainImage(Path<? extends MainImage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMainImage(PathMetadata metadata) {
        super(MainImage.class, metadata);
    }

}

