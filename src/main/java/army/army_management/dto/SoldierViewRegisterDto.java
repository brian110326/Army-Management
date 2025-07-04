package army.army_management.dto;

import army.army_management.entity.Position;
import army.army_management.entity.Rank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class SoldierViewRegisterDto {

    @NotBlank(message = "이름은 필수 사항입니다.")
    private String name;
    @NotBlank(message = "군번은 필수 사항입니다.")
    private String armyNumber;

    @NotNull(message = "생년월일은 필수입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bornDate;

    @NotNull(message = "입영날짜는 필수입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enlistDate;

    @NotNull(message = "계급 등록은 필수입니다.")
    private Rank rank;
    @NotNull(message = "직책 등록은 필수입니다.")
    private Position position;

    @NotNull(message = "소속 등록은 필수입니다.")
    private Long unitId;

}
