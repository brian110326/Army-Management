package army.army_management.dto;

import army.army_management.entity.LeaveStatus;
import army.army_management.entity.LeaveType;
import army.army_management.entity.Soldier;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestDto {

    private Soldier soldier;

    @NotNull(message = "시작일 등록은 필수입니다.")
    private LocalDate startDate;
    @NotNull(message = "종료일 등록은 필수입니다.")
    private LocalDate endDate;

    @NotNull(message = "휴가종류 등록은 필수입니다.")
    private LeaveType leaveType;

    @NotNull(message = "휴가 승인여부 등록은 필수입니다.")
    private LeaveStatus leaveStatus;

    private String reason;

}
