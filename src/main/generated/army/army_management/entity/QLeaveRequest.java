package army.army_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLeaveRequest is a Querydsl query type for LeaveRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLeaveRequest extends EntityPathBase<LeaveRequest> {

    private static final long serialVersionUID = 1427096841L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLeaveRequest leaveRequest = new QLeaveRequest("leaveRequest");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<LeaveStatus> leaveStatus = createEnum("leaveStatus", LeaveStatus.class);

    public final EnumPath<LeaveType> leaveType = createEnum("leaveType", LeaveType.class);

    public final StringPath reason = createString("reason");

    public final QSoldier soldier;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QLeaveRequest(String variable) {
        this(LeaveRequest.class, forVariable(variable), INITS);
    }

    public QLeaveRequest(Path<? extends LeaveRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLeaveRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLeaveRequest(PathMetadata metadata, PathInits inits) {
        this(LeaveRequest.class, metadata, inits);
    }

    public QLeaveRequest(Class<? extends LeaveRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.soldier = inits.isInitialized("soldier") ? new QSoldier(forProperty("soldier"), inits.get("soldier")) : null;
    }

}

