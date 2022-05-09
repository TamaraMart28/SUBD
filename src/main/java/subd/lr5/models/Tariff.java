package subd.lr5.models;

import javax.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tariff", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "in_city_call_cost")
    private Double in_city_call_cost;

    @Column(name = "out_city_call_cost")
    private Double out_city_call_cost;

    @Column(name = "is_active")
    private Boolean is_active;

    @OneToMany(mappedBy = "tariff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    public Tariff() { }

    public Tariff(String name, Double in_city_call_cost, Double out_city_call_cost, Boolean is_active) {
        this.name = name;
        this.in_city_call_cost = in_city_call_cost;
        this.out_city_call_cost = out_city_call_cost;
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Tariff {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", in_city_call_cost='" + in_city_call_cost + '\'' +
                ", out_city_call_cost='" + out_city_call_cost + '\'' +
                ", is_active='" + is_active + '\'' +
                '}' + "\n";
    }
}
