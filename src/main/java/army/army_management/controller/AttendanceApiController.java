package army.army_management.controller;

import army.army_management.dto.DutyEventDto;
import army.army_management.entity.Attendance;
import army.army_management.entity.DutyType;
import army.army_management.entity.Rank;
import army.army_management.entity.Soldier;
import army.army_management.repository.AttendanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
public class AttendanceApiController {

    private final AttendanceRepository attendanceRepository;

    @GetMapping("/api/attendances")
    public List<DutyEventDto> getAttendances(@RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                             LocalDate start,
                                             @RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                             LocalDate end
    ) {
        List<Attendance> attendanceList =
                attendanceRepository.findByWorkDateBetween(start, end);

        List<DutyEventDto> events = new ArrayList<>();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Attendance attendance : attendanceList) {
            Soldier soldier = attendance.getSoldier();
            Rank soldierRank = soldier.getRank();
            String soldierName = soldier.getName();

            DutyType dutyType = attendance.getDutyType();

            String title = soldierRank + " " + soldierName + " " + attendance.getDutyType();

            events.add(new DutyEventDto(title, soldierName, soldierRank, dutyType,
                    attendance.getCheckInTime().toString(),
                    attendance.getCheckOutTime().toString()));
        }

        return events;
    }

}
