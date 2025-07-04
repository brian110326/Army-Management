package army.army_management.repository;

import army.army_management.dto.SoldierSearchCondition;
import army.army_management.dto.SoldierViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SoldierRepositoryCustom {

    Page<SoldierViewDto> findAllSoldiers(SoldierSearchCondition condition, Pageable pageable);

}
