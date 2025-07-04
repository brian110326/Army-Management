package army.army_management.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
public class Unit {

    @Id
    @GeneratedValue
    @Column(name = "unit_id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToMany(mappedBy = "unit")
    private List<Soldier> soldiers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Unit parent;

    @OneToMany(mappedBy = "parent")
    @BatchSize(size = 20)
    private List<Unit> children = new ArrayList<>();

    public void addParent(Unit parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }

    public String getFullPath() {
        /*List<String> names = new ArrayList<>();
        Unit unit = this;
        while (unit != null) {
            names.add(unit.getName());
            unit = unit.getParent();
        }

        Collections.reverse(names);
        return String.join(" > ", names);*/

        return name;
    }

}
