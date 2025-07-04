package army.army_management.service;

import army.army_management.dto.LeaveRequestDto;
import army.army_management.dto.LeaveRequestSearchCondition;
import army.army_management.dto.LeaveRequestViewDto;
import army.army_management.entity.LeaveStatus;
import army.army_management.exception.DayIncorrectException;
import army.army_management.exception.UnsufficientLeaveDaysException;
import army.army_management.entity.LeaveRequest;
import army.army_management.entity.Soldier;
import army.army_management.repository.LeaveRequestRepository;
import army.army_management.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final SoldierRepository soldierRepository;

    @Transactional
    public LeaveRequestDto requestLeave(LeaveRequestDto dto, Long soldierId) {
        Soldier soldier = soldierRepository.findById(soldierId).get();

        acceptLeaveRequest(soldier, dto.getStartDate(), dto.getEndDate(), dto.getLeaveStatus());

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setSoldier(soldier);
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setLeaveType(dto.getLeaveType());
        leaveRequest.setLeaveStatus(dto.getLeaveStatus());
        leaveRequest.setReason(dto.getReason());

        soldier.addLeaveRequest(leaveRequest);

        leaveRequestRepository.save(leaveRequest);

        return dto;
    }

    public Optional<LeaveRequest> findLeaveRequest(Long id) {
        LeaveRequest lr = leaveRequestRepository.findById(id).get();
        return Optional.ofNullable(lr);
    }

    public Page<LeaveRequestViewDto> findAll(LeaveRequestSearchCondition condition,
                                             Pageable pageable) {
        return leaveRequestRepository.findAllLeaveRequests(condition, pageable);
    }

    private void acceptLeaveRequest(Soldier soldier, LocalDate start, LocalDate end,
                                    LeaveStatus status) {
        int leaveDays = getLeaveDays(start, end);
        int updateRemain = soldier.getLeftDays() - leaveDays;

        if (end.isBefore(start)) {
            throw new DayIncorrectException("종료일이 시작일보다 이전입니다.");
        }

        if (updateRemain < 0) {
            throw new UnsufficientLeaveDaysException("남은 휴가 일수가 부족합니다.");
        }

        if (status.equals(LeaveStatus.승인)) {
            soldier.setLeftDays(updateRemain);
        }

    }

    private int getLeaveDays(LocalDate start, LocalDate end) {
        return (int) ChronoUnit.DAYS.between(start, end) + 1;
    }

    @Transactional
    public void changeToUnaccepted(Long id) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(id).get();
        leaveRequest.setLeaveStatus(LeaveStatus.반려);

        Soldier soldier = leaveRequest.getSoldier();
        int leaveDays = getLeaveDays(leaveRequest.getStartDate(), leaveRequest.getEndDate());

        soldier.setLeftDays(soldier.getLeftDays() + leaveDays);
    }

}
