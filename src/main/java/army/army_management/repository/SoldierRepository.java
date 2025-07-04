package army.army_management.repository;

import army.army_management.entity.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface SoldierRepository extends JpaRepository<Soldier, Long>,
        SoldierRepositoryCustom {

    @Query("select s from Soldier s join fetch s.unit u " +
            "where s.id = :id")
    Optional<Soldier> findWithUnitById(@Param("id") Long id);

}
