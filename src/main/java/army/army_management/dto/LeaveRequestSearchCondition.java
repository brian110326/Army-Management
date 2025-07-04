package army.army_management.dto;

import army.army_management.entity.LeaveStatus;
import lombok.Data;

@Data
public class LeaveRequestSearchCondition {

    private String soldierName;
    private LeaveStatus status;

    public LeaveRequestSearchCondition(String soldierName, LeaveStatus status) {
        this.soldierName = soldierName;
        this.status = status;
    }
}
