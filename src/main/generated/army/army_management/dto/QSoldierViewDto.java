package army.army_management.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * army.army_management.dto.QSoldierViewDto is a Querydsl Projection type for SoldierViewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QSoldierViewDto extends ConstructorExpression<SoldierViewDto> {

    private static final long serialVersionUID = 713143169L;

    public QSoldierViewDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> armyNumber, com.querydsl.core.types.Expression<java.time.LocalDateTime> bornDate, com.querydsl.core.types.Expression<army.army_management.entity.Rank> rank, com.querydsl.core.types.Expression<army.army_management.entity.Position> position, com.querydsl.core.types.Expression<java.time.LocalDateTime> enlistDate, com.querydsl.core.types.Expression<? extends army.army_management.entity.Unit> unit, com.querydsl.core.types.Expression<String> unitFullPath) {
        super(SoldierViewDto.class, new Class<?>[]{long.class, String.class, String.class, java.time.LocalDateTime.class, army.army_management.entity.Rank.class, army.army_management.entity.Position.class, java.time.LocalDateTime.class, army.army_management.entity.Unit.class, String.class}, id, name, armyNumber, bornDate, rank, position, enlistDate, unit, unitFullPath);
    }

}

