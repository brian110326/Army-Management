package army.army_management.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * army.army_management.dto.QLeaveRequestViewDto is a Querydsl Projection type for LeaveRequestViewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QLeaveRequestViewDto extends ConstructorExpression<LeaveRequestViewDto> {

    private static final long serialVersionUID = 435148089L;

    public QLeaveRequestViewDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> soldierName, com.querydsl.core.types.Expression<army.army_management.entity.Rank> soldierRank, com.querydsl.core.types.Expression<String> unitName, com.querydsl.core.types.Expression<java.time.LocalDate> startDate, com.querydsl.core.types.Expression<java.time.LocalDate> endDate, com.querydsl.core.types.Expression<army.army_management.entity.LeaveType> leaveType, com.querydsl.core.types.Expression<army.army_management.entity.LeaveStatus> leaveStatus, com.querydsl.core.types.Expression<String> reason) {
        super(LeaveRequestViewDto.class, new Class<?>[]{long.class, String.class, army.army_management.entity.Rank.class, String.class, java.time.LocalDate.class, java.time.LocalDate.class, army.army_management.entity.LeaveType.class, army.army_management.entity.LeaveStatus.class, String.class}, id, soldierName, soldierRank, unitName, startDate, endDate, leaveType, leaveStatus, reason);
    }

}

