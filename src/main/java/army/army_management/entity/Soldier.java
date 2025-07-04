package army.army_management.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Soldier {

    @Id
    @GeneratedValue
    @Column(name = "soldier_id")
    private Long id;

    private String name;
    private String armyNumber;

    private LocalDateTime bornDate;

    @Enumerated(EnumType.STRING)
    private Rank rank; // 계급

    @Enumerated(EnumType.STRING)
    private Position position; // 직책

    private LocalDateTime enlistDate; // 입영일자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    // 휴가 잔여일수
    private int leftDays = 20;

    private String unitFullPath;

    @OneToMany(mappedBy = "soldier")
    private List<Attendance> attendance = new ArrayList<>();

    @OneToMany(mappedBy = "soldier")
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    public void setUnit(Unit unit) {
        this.unit = unit;
        unit.getSoldiers().add(this);
    }

    public void addAttendance(Attendance attendance) {
        this.attendance.add(attendance);
        attendance.setSoldier(this);
    }

    public void addLeaveRequest(LeaveRequest leaveRequest) {
        this.leaveRequests.add(leaveRequest);
        leaveRequest.setSoldier(this);
    }

}
