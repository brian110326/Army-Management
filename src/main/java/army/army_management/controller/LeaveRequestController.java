package army.army_management.controller;

import army.army_management.dto.LeaveRequestDto;
import army.army_management.dto.LeaveRequestSearchCondition;
import army.army_management.dto.LeaveRequestViewDto;
import army.army_management.entity.LeaveStatus;
import army.army_management.entity.LeaveType;
import army.army_management.exception.DayIncorrectException;
import army.army_management.exception.UnsufficientLeaveDaysException;
import army.army_management.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @GetMapping("/leave-requests")
    public String viewAllLeaveRequests(Model model, LeaveRequestSearchCondition condition,
                                       @PageableDefault(size = 10) Pageable pageable) {
        Page<LeaveRequestViewDto> result = leaveRequestService.findAll(condition, pageable);

        int currentPage = result.getNumber();
        int totalPages = result.getTotalPages();

        int pageBlockSize = 5;
        int startPage = (currentPage / pageBlockSize) * pageBlockSize;
        int endpage = Math.min(startPage + pageBlockSize - 1, totalPages - 1);

        model.addAttribute("requests", result);

        model.addAttribute("param", condition);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endpage);

        LeaveStatus[] statusList = LeaveStatus.values();
        model.addAttribute("statusList", statusList);
        model.addAttribute("statusCurrent", condition.getStatus());

        return "leave/list";
    }

    @GetMapping("/{id}/leave-register")
    public String getLeaveRegister(@PathVariable("id") Long soldierId, Model model) {
        model.addAttribute("dto", new LeaveRequestDto());
        model.addAttribute("soldierId", soldierId);
        model.addAttribute("leaveTypes", LeaveType.values());
        model.addAttribute("leaveStatuses", LeaveStatus.values());
        return "leave/register";
    }

    @PostMapping("/{id}/leave-register")
    public String postLeaveRegister(@PathVariable("id") Long soldierId,
                                    @Validated @ModelAttribute("dto") LeaveRequestDto dto,
                                    BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("soldierId", soldierId);
            model.addAttribute("leaveTypes", LeaveType.values());
            model.addAttribute("leaveStatuses", LeaveStatus.values());
            return "leave/register";
        }

        try {
            leaveRequestService.requestLeave(dto, soldierId);
        } catch (DayIncorrectException de) {
            model.addAttribute("soldierId", soldierId);
            model.addAttribute("leaveTypes", LeaveType.values());
            model.addAttribute("leaveStatuses", LeaveStatus.values());

            model.addAttribute("dayError", de.getMessage());
            return "leave/register";
        } catch (UnsufficientLeaveDaysException e) {
            model.addAttribute("soldierId", soldierId);
            model.addAttribute("leaveTypes", LeaveType.values());
            model.addAttribute("leaveStatuses", LeaveStatus.values());

            model.addAttribute("leaveError", e.getMessage());
            return "leave/register";
        }

        return "redirect:/leave-requests";
    }

}
