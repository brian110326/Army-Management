package army.army_management.controller;

import army.army_management.dto.SoldierSearchCondition;
import army.army_management.dto.SoldierViewDto;
import army.army_management.entity.Soldier;
import army.army_management.repository.SoldierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class SoldierApiController {

    private final SoldierRepository soldierRepository;

    @GetMapping("/api/soldiers")
    public Page<SoldierViewDto> getSoldiers(SoldierSearchCondition condition, Pageable pageable) {
        return soldierRepository.findAllSoldiers(condition, pageable);
    }

}
