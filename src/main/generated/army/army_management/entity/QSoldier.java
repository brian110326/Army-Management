package army.army_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSoldier is a Querydsl query type for Soldier
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSoldier extends EntityPathBase<Soldier> {

    private static final long serialVersionUID = -1791028815L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSoldier soldier = new QSoldier("soldier");

    public final StringPath armyNumber = createString("armyNumber");

    public final ListPath<Attendance, QAttendance> attendance = this.<Attendance, QAttendance>createList("attendance", Attendance.class, QAttendance.class, PathInits.DIRECT2);

    public final DateTimePath<java.time.LocalDateTime> bornDate = createDateTime("bornDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> enlistDate = createDateTime("enlistDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<LeaveRequest, QLeaveRequest> leaveRequests = this.<LeaveRequest, QLeaveRequest>createList("leaveRequests", LeaveRequest.class, QLeaveRequest.class, PathInits.DIRECT2);

    public final NumberPath<Integer> leftDays = createNumber("leftDays", Integer.class);

    public final StringPath name = createString("name");

    public final EnumPath<Position> position = createEnum("position", Position.class);

    public final EnumPath<Rank> rank = createEnum("rank", Rank.class);

    public final QUnit unit;

    public final StringPath unitFullPath = createString("unitFullPath");

    public QSoldier(String variable) {
        this(Soldier.class, forVariable(variable), INITS);
    }

    public QSoldier(Path<? extends Soldier> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSoldier(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSoldier(PathMetadata metadata, PathInits inits) {
        this(Soldier.class, metadata, inits);
    }

    public QSoldier(Class<? extends Soldier> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.unit = inits.isInitialized("unit") ? new QUnit(forProperty("unit"), inits.get("unit")) : null;
    }

}

