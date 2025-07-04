package army.army_management.repository;

import army.army_management.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query("select a from Attendance a join fetch a.soldier s " +
            "where a.workDate between :start and :end")
    List<Attendance> findByWorkDateBetween(@Param("start") LocalDate start,
                                           @Param("end") LocalDate end);

}
