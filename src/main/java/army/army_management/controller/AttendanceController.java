package army.army_management.controller;

import army.army_management.dto.AttendanceRegisterDto;
import army.army_management.entity.Attendance;
import army.army_management.entity.DutyType;
import army.army_management.repository.AttendanceRepository;
import army.army_management.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final SoldierRepository soldierRepository;

    @GetMapping("/attendance/calendar")
    public String showCalendar() {
        return "attendance/todayCalendar";
    }


    @GetMapping("/{id}/attendance/register")
    public String getRegister(@PathVariable("id") Long id,
                              Model model) {
        AttendanceRegisterDto dto = new AttendanceRegisterDto();
        dto.setSoldierId(id);
        model.addAttribute("dto", dto);
        model.addAttribute("duties", DutyType.values());
        model.addAttribute("id", id);
        return "attendance/register";
    }

    @PostMapping("/{id}/attendance/register")
    public String postRegister(@PathVariable("id") Long id,
                               @Validated @ModelAttribute
                               AttendanceRegisterDto dto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("duties", DutyType.values());
            model.addAttribute("id", id);
            return "attendance/register";
        }

        Attendance attendance = new Attendance();

        attendance.setSoldier(soldierRepository.findById(dto.getSoldierId()).get());
        attendance.setWorkDate(dto.getWorkDate());
        attendance.setDutyType(dto.getDutyType());
        attendance.setCheckInTime(dto.getCheckInTime());
        attendance.setCheckOutTime(dto.getCheckOutTime());

        attendanceRepository.save(attendance);

        return "redirect:/attendance/calendar";
    }

}
