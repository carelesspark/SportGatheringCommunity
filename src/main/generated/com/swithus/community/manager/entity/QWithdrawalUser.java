package com.swithus.community.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWithdrawalUser is a Querydsl query type for WithdrawalUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWithdrawalUser extends EntityPathBase<WithdrawalUser> {

    private static final long serialVersionUID = -835689567L;

    public static final QWithdrawalUser withdrawalUser = new QWithdrawalUser("withdrawalUser");

    public final com.swithus.community.global.entity.QBaseEntity _super = new com.swithus.community.global.entity.QBaseEntity(this);

    public final StringPath deleteReason = createString("deleteReason");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath userId = createString("userId");

    public QWithdrawalUser(String variable) {
        super(WithdrawalUser.class, forVariable(variable));
    }

    public QWithdrawalUser(Path<? extends WithdrawalUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWithdrawalUser(PathMetadata metadata) {
        super(WithdrawalUser.class, metadata);
    }

}

