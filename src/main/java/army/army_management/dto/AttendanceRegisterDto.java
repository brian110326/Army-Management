package army.army_management.dto;

import army.army_management.entity.DutyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AttendanceRegisterDto {

    private Long soldierId;

    @NotNull(message = "근무일 등록은 필수입니다.")
    private LocalDate workDate;

    @NotNull(message = "근무유형 등록은 필수입니다.")
    private DutyType dutyType;

    @NotNull(message = "근무시작 시간 등록은 필수입니다.")
    private LocalDateTime checkInTime;

    @NotNull(message = "근무종료 시간 등록은 필수입니다.")
    private LocalDateTime checkOutTime;

    public AttendanceRegisterDto(Long soldierId, LocalDate workDate,
                                 DutyType dutyType,
                                 LocalDateTime checkInTime,
                                 LocalDateTime checkOutTime) {
        this.soldierId = soldierId;
        this.workDate = workDate;
        this.dutyType = dutyType;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

}
