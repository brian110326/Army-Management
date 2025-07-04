package army.army_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Attendance {

    @Id
    @GeneratedValue
    @Column(name = "attendance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soldier_id")
    private Soldier soldier;

    private LocalDate workDate;

    @Enumerated(EnumType.STRING)
    private DutyType dutyType;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

}
