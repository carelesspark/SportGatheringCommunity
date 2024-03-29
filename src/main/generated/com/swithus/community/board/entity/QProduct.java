package com.swithus.community.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = 1991692405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProduct product = new QProduct("product");

    public final StringPath content = createString("content");

    public final QProductCtgr ctgr;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Byte> isDone = createNumber("isDone", Byte.class);

    public final StringPath name = createString("name");

    public final com.swithus.community.global.entity.QSports sports;

    public final com.swithus.community.user.entity.QUser user;

    public final NumberPath<Integer> visitCount = createNumber("visitCount", Integer.class);

    public QProduct(String variable) {
        this(Product.class, forVariable(variable), INITS);
    }

    public QProduct(Path<? extends Product> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProduct(PathMetadata metadata, PathInits inits) {
        this(Product.class, metadata, inits);
    }

    public QProduct(Class<? extends Product> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ctgr = inits.isInitialized("ctgr") ? new QProductCtgr(forProperty("ctgr")) : null;
        this.sports = inits.isInitialized("sports") ? new com.swithus.community.global.entity.QSports(forProperty("sports")) : null;
        this.user = inits.isInitialized("user") ? new com.swithus.community.user.entity.QUser(forProperty("user")) : null;
    }

}

