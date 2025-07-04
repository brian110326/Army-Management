package army.army_management.dto;

import army.army_management.entity.Position;
import army.army_management.entity.Rank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SoldierSearchCondition {

    private String soldierName;
    private Rank rank;
    private Position position;

    private Integer year;
    private Integer month;
    private Integer day;

    private Long unitId;

}
