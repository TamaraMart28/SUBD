package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "employee", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "work_place ")
    private String work_place;

    @Column(name = "position ")
    private String position ;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    public Employee() { }

    public Employee(String full_name, String work_place, String position) {
        this.full_name = full_name;
        this.work_place = work_place;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", work_place='" + work_place + '\'' +
                ", position='" + position + '\'' +
                '}' + "\n";
    }
}
