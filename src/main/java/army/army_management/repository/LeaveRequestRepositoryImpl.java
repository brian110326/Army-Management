package army.army_management.repository;

import army.army_management.dto.LeaveRequestSearchCondition;
import army.army_management.dto.LeaveRequestViewDto;
import army.army_management.dto.QLeaveRequestViewDto;
import army.army_management.entity.LeaveStatus;
import army.army_management.entity.QLeaveRequest;
import army.army_management.entity.QSoldier;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public class LeaveRequestRepositoryImpl implements LeaveRequestRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LeaveRequestRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<LeaveRequestViewDto> findAllLeaveRequests(LeaveRequestSearchCondition condition,
                                                          Pageable pageable) {
        QLeaveRequest lr = QLeaveRequest.leaveRequest;

        QueryResults<LeaveRequestViewDto> results =
                queryFactory.select(new QLeaveRequestViewDto(lr.id, lr.soldier.name,
                                lr.soldier.rank, lr.soldier.unit.name,
                                lr.startDate, lr.endDate,
                                lr.leaveType, lr.leaveStatus, lr.reason))
                        .from(lr)
                        .where(soldierNameEq(condition.getSoldierName()),
                                leaveStatusEq(condition.getStatus()))
                        .orderBy(lr.id.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();

        List<LeaveRequestViewDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression soldierNameEq(String name) {
        QSoldier soldier = QSoldier.soldier;

        if (StringUtils.isEmpty(name)) {
            return null;
        }

        return soldier.name.like("%" + name + "%");
    }

    private BooleanExpression leaveStatusEq(LeaveStatus status) {
        QLeaveRequest lr = QLeaveRequest.leaveRequest;

        if (status == null) {
            return null;
        }

        return lr.leaveStatus.eq(status);
    }

}
