package army.army_management.repository;

import army.army_management.dto.LeaveRequestSearchCondition;
import army.army_management.dto.LeaveRequestViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaveRequestRepositoryCustom {

    Page<LeaveRequestViewDto> findAllLeaveRequests(LeaveRequestSearchCondition condition,
                                                   Pageable pageable);

}
