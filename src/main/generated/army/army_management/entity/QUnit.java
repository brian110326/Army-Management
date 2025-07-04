package army.army_management.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUnit is a Querydsl query type for Unit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUnit extends EntityPathBase<Unit> {

    private static final long serialVersionUID = 781832117L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUnit unit = new QUnit("unit");

    public final ListPath<Unit, QUnit> children = this.<Unit, QUnit>createList("children", Unit.class, QUnit.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QUnit parent;

    public final ListPath<Soldier, QSoldier> soldiers = this.<Soldier, QSoldier>createList("soldiers", Soldier.class, QSoldier.class, PathInits.DIRECT2);

    public final EnumPath<UnitType> unitType = createEnum("unitType", UnitType.class);

    public QUnit(String variable) {
        this(Unit.class, forVariable(variable), INITS);
    }

    public QUnit(Path<? extends Unit> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUnit(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUnit(PathMetadata metadata, PathInits inits) {
        this(Unit.class, metadata, inits);
    }

    public QUnit(Class<? extends Unit> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QUnit(forProperty("parent"), inits.get("parent")) : null;
    }

}

