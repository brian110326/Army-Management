package army.army_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AttendanceController {

    @GetMapping("/attendance/calendar")
    public String showCalendar() {
        return "attendance/todayCalendar";
    }

}
