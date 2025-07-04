package army.army_management.repository;

import army.army_management.dto.QSoldierViewDto;
import army.army_management.dto.SoldierSearchCondition;
import army.army_management.dto.SoldierViewDto;
import army.army_management.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SoldierRepositoryImpl implements SoldierRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final UnitRepository unitRepository;

    public SoldierRepositoryImpl(EntityManager em, UnitRepository unitRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.unitRepository = unitRepository;
    }

    @Override
    public Page<SoldierViewDto> findAllSoldiers(SoldierSearchCondition condition,
                                                Pageable pageable) {
        QSoldier soldier = QSoldier.soldier;
        QUnit unit = QUnit.unit;

        QueryResults<SoldierViewDto> results = queryFactory.select(new QSoldierViewDto(
                        soldier.id,
                        soldier.name, soldier.armyNumber, soldier.bornDate,
                        soldier.rank, soldier.position, soldier.enlistDate,
                        soldier.unit, soldier.unitFullPath
                )).from(soldier)
                .where(soldierNameEq(condition.getSoldierName()),
                        soldierRankEq(condition.getRank()),
                        soldierPositionEq(condition.getPosition()),
                        soldierUnitEq(condition.getUnitId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<SoldierViewDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private List<Long> getAllChildUnitIds(Unit unit) {
        List<Long> ids = new ArrayList<>();
        ids.add(unit.getId());

        for (Unit child : unit.getChildren()) {
            ids.addAll(getAllChildUnitIds(child));
        }

        return ids;
    }

    private BooleanExpression soldierUnitEq(Long unitId) {
        QSoldier soldier = QSoldier.soldier;

        if (unitId == null) {
            return null;
        }

        Unit rootUnit = unitRepository.findByIdWithChildren(unitId).orElse(null);

        if (rootUnit == null) {
            return null;
        }

        List<Long> ids = getAllChildUnitIds(rootUnit);

        return soldier.unit.id.in(ids);
    }


    private BooleanExpression soldierNameEq(String name) {
        QSoldier soldier = QSoldier.soldier;

        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return soldier.name.like("%" + name + "%");
    }

    private BooleanExpression soldierRankEq(Rank rank) {
        QSoldier soldier = QSoldier.soldier;

        if (rank == null) {
            return null;
        }

        return soldier.rank.eq(rank);
    }

    private BooleanExpression soldierPositionEq(Position position) {
        QSoldier soldier = QSoldier.soldier;

        if (position == null) {
            return null;
        }

        return soldier.position.eq(position);
    }

}
