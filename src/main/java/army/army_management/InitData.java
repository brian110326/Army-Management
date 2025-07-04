package army.army_management;

import army.army_management.entity.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Profile(value = "local")
@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataService initDataService;

    @PostConstruct
    public void init() {
        initDataService.init();
    }

    @Component
    static class InitDataService {
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init() {
            // 사단 설정
            for (int i = 1; i <= 10; i++) {
                Unit unit = new Unit();
                unit.setName(i + "사단");
                unit.setUnitType(UnitType.대대);
                em.persist(unit);

                // 중대 설정
                for (int j = 1; j <= 2; j++) {
                    Unit company1 = new Unit();
                    company1.setName(i + "사단 " + j + "중대");
                    company1.setUnitType(UnitType.중대);
                    company1.setParent(unit);
                    em.persist(company1);

                    // 소대 설정
                    for (int k = 1; k <= 3; k++) {
                        Unit company2 = new Unit();
                        company2.setName(i + "사단 " + j + "중대 " + k + "소대");
                        company2.setUnitType(UnitType.소대);
                        company2.setParent(company1);
                        em.persist(company2);

                        //분대 설정
                        for (int l = 1; l <= 2; l++) {
                            Unit company3 = new Unit();
                            company3.setName(i + "사단 " + j + "중대 " + k + "소대 " + l + "분대");
                            company3.setUnitType(UnitType.분대);
                            company3.setParent(company2);
                            em.persist(company3);

                            // 병사 생성
                            for (int s = 0; s < 5; s++) {
                                Soldier soldier = new Soldier();
                                soldier.setName("병사_" + i + "_" + j + "_" + k + "_" + l + "_" + s);
                                soldier.setArmyNumber("19-123456");
                                soldier.setUnit(company3);
                                soldier.setBornDate(LocalDateTime.of(1990 + i, 1 + i,
                                        1 + i,0,0,0));
                                soldier.setEnlistDate(LocalDateTime.of(1990 + i, 1 + i,
                                        1 + i,0,0,0));
                                soldier.setRank(Rank.병장);
                                soldier.setPosition(Position.소총수);
                                em.persist(soldier);
                            }
                        }
                    }
                }
            }

            for (long i = 1; i <= 100; i++) {
                Soldier soldier = em.find(Soldier.class, i);
                if (soldier == null) continue;

                LeaveRequest request = new LeaveRequest();
                request.setSoldier(soldier);
                request.setStartDate(LocalDate.now().plusDays((int) i));
                request.setEndDate(LocalDate.now().plusDays((int) i + 3));
                request.setLeaveType(LeaveType.연가); // enum 값 중 하나
                request.setLeaveStatus(i % 2 == 0 ? LeaveStatus.승인 : LeaveStatus.반려); // 반은 승인, 반은 반려
                request.setReason("자동 생성 휴가 신청 #" + i);

                em.persist(request);
            }
        }
    }

}
