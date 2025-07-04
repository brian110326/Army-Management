package army.army_management.controller;

import army.army_management.dto.SoldierViewRegisterDto;
import army.army_management.dto.SoldierSearchCondition;
import army.army_management.dto.SoldierViewDto;
import army.army_management.entity.Position;
import army.army_management.entity.Rank;
import army.army_management.entity.Soldier;
import army.army_management.entity.Unit;
import army.army_management.repository.SoldierRepository;
import army.army_management.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class SoldierController {

    private final SoldierRepository soldierRepository;
    private final UnitRepository unitRepository;

    @GetMapping("/soldiers")
    public String getAllSoldiers(Model model, SoldierSearchCondition condition,
                                 @PageableDefault(size = 10) Pageable pageable) {
        Page<SoldierViewDto> soldiers = soldierRepository.findAllSoldiers(condition, pageable);

        int currentPage = soldiers.getNumber();
        int totalPages = soldiers.getTotalPages();

        int pageBlockSize = 5;
        int startPage = (currentPage / pageBlockSize) * pageBlockSize;
        int endpage = Math.min(startPage + pageBlockSize - 1, totalPages - 1);

        List<Unit> divisions = unitRepository.findTopLevelUnits();
        Rank[] ranks = Rank.values();

        model.addAttribute("soldiers", soldiers);
        model.addAttribute("condition", condition);

        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endpage);

        model.addAttribute("divisions", divisions);
        model.addAttribute("ranks", ranks);

        model.addAttribute("rank", condition.getRank());
        model.addAttribute("position", condition.getPosition());
        model.addAttribute("positions", Position.values());

        return "soldier/soldiers";
    }

    @GetMapping("/soldiers/register")
    public String getRegister(Model model) {
        List<Unit> leafUnits = unitRepository.findLeafUnits();

        model.addAttribute("leafUnits", leafUnits);
        model.addAttribute("soldierDto", new SoldierViewRegisterDto());

        Rank[] ranks = Rank.values();
        model.addAttribute("ranks", ranks);
        model.addAttribute("positions", Position.values());

        return "soldier/register";
    }

    @PostMapping("/soldiers/register")
    public String postRegister(@Validated @ModelAttribute("soldierDto")
                               SoldierViewRegisterDto soldierRegisterDto,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            List<Unit> leafUnits = unitRepository.findLeafUnits();

            model.addAttribute("leafUnits", leafUnits);

            Rank[] ranks = Rank.values();
            model.addAttribute("ranks", ranks);
            model.addAttribute("positions", Position.values());
            return "soldier/register";
        }

        Unit unit = unitRepository.findById(soldierRegisterDto.getUnitId()).get();

        Soldier soldier = new Soldier();
        soldier.setName(soldierRegisterDto.getName());
        soldier.setArmyNumber(soldierRegisterDto.getArmyNumber());
        soldier.setBornDate(soldierRegisterDto.getBornDate().atStartOfDay());
        soldier.setEnlistDate(soldierRegisterDto.getEnlistDate().atStartOfDay());
        soldier.setRank(soldierRegisterDto.getRank());
        soldier.setPosition(soldierRegisterDto.getPosition());
        soldier.setUnit(unit);
        soldier.setUnitFullPath(unit.getFullPath());

        soldierRepository.save(soldier);

        redirectAttributes.addAttribute("id", soldier.getId());
        redirectAttributes.addFlashAttribute("unitName", unit.getName());

        return "redirect:/soldiers/{id}";
    }

    @GetMapping("/soldiers/{id}")
    public String getSoldier(@PathVariable("id") Long id, Model model) {
        Soldier soldier = soldierRepository.findWithUnitById(id).get();
        SoldierViewRegisterDto dto = new SoldierViewRegisterDto();

        dto.setName(soldier.getName());
        dto.setArmyNumber(soldier.getArmyNumber());
        dto.setBornDate(soldier.getBornDate().toLocalDate());
        dto.setEnlistDate(soldier.getEnlistDate().toLocalDate());
        dto.setRank(soldier.getRank());
        dto.setPosition(soldier.getPosition());

        dto.setUnitId(soldier.getUnit().getId());
        Unit unit = unitRepository.findById(dto.getUnitId()).get();

        model.addAttribute("soldierDto", dto);
        model.addAttribute("unitName", unit.getName());
        model.addAttribute("id", soldier.getId());

        return "soldier/view";
    }

    @GetMapping("/soldiers/{id}/edit")
    public String getUpdate(@PathVariable("id") Long id, Model model) {
        Soldier soldier = soldierRepository.findById(id).get();
        SoldierViewRegisterDto dto = new SoldierViewRegisterDto();

        dto.setName(soldier.getName());
        dto.setArmyNumber(soldier.getArmyNumber());
        dto.setBornDate(soldier.getBornDate().toLocalDate());
        dto.setEnlistDate(soldier.getEnlistDate().toLocalDate());
        dto.setRank(soldier.getRank());
        dto.setPosition(soldier.getPosition());
        dto.setUnitId(soldier.getUnit().getId());

        model.addAttribute("soldierDto", dto);

        List<Unit> leafUnits = unitRepository.findLeafUnits();

        model.addAttribute("leafUnits", leafUnits);

        Rank[] ranks = Rank.values();
        model.addAttribute("ranks", ranks);
        model.addAttribute("positions", Position.values());

        return "soldier/update";
    }

    @Transactional
    @PostMapping("/soldiers/{id}/edit")
    public String postUpdate(@PathVariable("id") Long id,
                             @Validated @ModelAttribute("soldierDto")
                             SoldierViewRegisterDto dto,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<Unit> leafUnits = unitRepository.findLeafUnits();

            model.addAttribute("leafUnits", leafUnits);

            Rank[] ranks = Rank.values();
            model.addAttribute("ranks", ranks);
            model.addAttribute("positions", Position.values());
            return "soldier/update";
        }

        Unit unit = unitRepository.findById(dto.getUnitId()).get();

        Soldier soldier = soldierRepository.findWithUnitById(id).get();
        soldier.setName(dto.getName());
        soldier.setArmyNumber(dto.getArmyNumber());
        soldier.setRank(dto.getRank());
        soldier.setBornDate(dto.getBornDate().atStartOfDay());
        soldier.setEnlistDate(dto.getEnlistDate().atStartOfDay());
        soldier.setPosition(dto.getPosition());
        soldier.setUnit(unit);
        soldier.setUnitFullPath(unit.getFullPath());

        redirectAttributes.addAttribute("id", id);

        return "redirect:/soldiers/{id}";
    }

    @GetMapping("/soldiers/{id}/remove")
    public String getSoldierRemove(@PathVariable("id") Long id,
                                   Model model) {
        Soldier soldier = soldierRepository.findById(id).get();
        SoldierViewRegisterDto dto = new SoldierViewRegisterDto();

        dto.setName(soldier.getName());
        dto.setArmyNumber(soldier.getArmyNumber());
        dto.setBornDate(soldier.getBornDate().toLocalDate());
        dto.setEnlistDate(soldier.getEnlistDate().toLocalDate());
        dto.setRank(soldier.getRank());
        dto.setPosition(soldier.getPosition());
        dto.setUnitId(soldier.getUnit().getId());

        Unit unit = unitRepository.findById(dto.getUnitId()).get();

        model.addAttribute("soldierDto", dto);
        model.addAttribute("unitName", unit.getName());

        return "soldier/remove";
    }

    @PostMapping("/soldiers/{id}/remove")
    @Transactional
    public String postSoldierRemove(@PathVariable("id") Long id) {
        Soldier soldier = soldierRepository.findById(id).get();
        soldierRepository.delete(soldier);

        return "redirect:/soldiers";
    }

}
