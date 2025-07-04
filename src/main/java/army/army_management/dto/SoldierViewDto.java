package army.army_management.dto;

import army.army_management.entity.Position;
import army.army_management.entity.Rank;
import army.army_management.entity.Unit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class SoldierViewDto {

    private Long id;
    private String name;
    private String armyNumber;
    private LocalDateTime bornDate;
    private Rank rank;
    private Position position;
    private LocalDateTime enlistDate;
    @JsonIgnore
    private Unit unit;

    private String unitFullPath;

    private String buildUnitPath(Unit unit) {
        List<String> names = new ArrayList<>();
        while (unit != null) {
            names.add(unit.getName());
            unit = unit.getParent();
        }

        Collections.reverse(names);
        return String.join(" > ", names);
    }

    @QueryProjection

    public SoldierViewDto(Long id, String name, String armyNumber, LocalDateTime bornDate, Rank rank,
                          Position position, LocalDateTime enlistDate, Unit unit, String unitFullPath) {
        this.id = id;
        this.name = name;
        this.armyNumber = armyNumber;
        this.bornDate = bornDate;
        this.rank = rank;
        this.position = position;
        this.enlistDate = enlistDate;
        this.unit = unit;
        this.unitFullPath = unitFullPath;
    }
}
