package army.army_management.dto;

import army.army_management.entity.DutyType;
import army.army_management.entity.Rank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DutyEventDto {

    private String title;
    private String soldierName;
    private Rank rank;
    private DutyType dutyType;

    private String start;
    private String end;

    public DutyEventDto(String title, String soldierName, Rank rank, DutyType dutyType,
                        String checkInTime, String checkOutTime) {
        this.title = title;
        this.soldierName = soldierName;
        this.rank = rank;
        this.dutyType = dutyType;

        this.start = checkInTime.toString();
        this.end = checkOutTime.toString();
    }
}
