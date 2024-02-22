package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWithdrawalGathering is a Querydsl query type for WithdrawalGathering
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWithdrawalGathering extends EntityPathBase<WithdrawalGathering> {

    private static final long serialVersionUID = 921711089L;

    public static final QWithdrawalGathering withdrawalGathering = new QWithdrawalGathering("withdrawalGathering");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath deleteReason = createString("deleteReason");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath leader = createString("leader");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QWithdrawalGathering(String variable) {
        super(WithdrawalGathering.class, forVariable(variable));
    }

    public QWithdrawalGathering(Path<? extends WithdrawalGathering> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWithdrawalGathering(PathMetadata metadata) {
        super(WithdrawalGathering.class, metadata);
    }

}

