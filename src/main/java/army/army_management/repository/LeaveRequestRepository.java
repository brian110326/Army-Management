package army.army_management.repository;

import army.army_management.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>,
        LeaveRequestRepositoryCustom {

    @Query("select lr from LeaveRequest lr join fetch lr.soldier s " +
            "join fetch s.unit u")
    List<LeaveRequest> findAllWithSoldier();
}
