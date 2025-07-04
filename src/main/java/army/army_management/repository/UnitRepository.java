package army.army_management.repository;

import army.army_management.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UnitRepository extends JpaRepository<Unit, Long> {

    @Query("select u from Unit u where u.parent is null")
    List<Unit> findTopLevelUnits();

    @Query("select u from Unit u join fetch u.children where u.id = :id")
    Optional<Unit> findByIdWithChildren(@Param("id") Long id);

    @Query("select u from Unit u where u.children is empty")
    List<Unit> findLeafUnits();

}
