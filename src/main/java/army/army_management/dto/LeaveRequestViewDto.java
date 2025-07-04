package army.army_management.dto;

import army.army_management.entity.LeaveRequest;
import army.army_management.entity.LeaveStatus;
import army.army_management.entity.LeaveType;
import army.army_management.entity.Rank;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestViewDto {
    private Long id;
    private String soldierName;
    private Rank soldierRank;
    private String unitName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
    private String reason;

    @QueryProjection
    public LeaveRequestViewDto(Long id, String soldierName, Rank soldierRank,
                               String unitName, LocalDate startDate,
                               LocalDate endDate, LeaveType leaveType,
                               LeaveStatus leaveStatus, String reason) {
        this.id = id;
        this.soldierName = soldierName;
        this.soldierRank = soldierRank;
        this.unitName = unitName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.leaveStatus = leaveStatus;
        this.reason = reason;
    }

    public LeaveRequestViewDto(LeaveRequest leaveRequest) {
        this.id = leaveRequest.getId();
        this.soldierName = leaveRequest.getSoldier().getName();
        this.soldierRank = leaveRequest.getSoldier().getRank();
        this.unitName = leaveRequest.getSoldier().getUnit().getName(); // 단위 부대 이름
        this.startDate = leaveRequest.getStartDate();
        this.endDate = leaveRequest.getEndDate();
        this.leaveType = leaveRequest.getLeaveType();
        this.leaveStatus = leaveRequest.getLeaveStatus();
        this.reason = leaveRequest.getReason();
    }

}

