package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "contract", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "purchase_date")
    private Date purchase_date;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "is_active")
    private Boolean is_active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tariffid")
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clientid")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeid")
    private Employee employee;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expenses> expenses;

    public Contract() {}

    public Contract(Date purchase_date, String phone_number, Boolean is_active, Tariff tariff, Client client, Employee employee) {
        this.purchase_date = purchase_date;
        this.phone_number = phone_number;
        this.is_active = is_active;
        this.tariff = tariff;
        this.client = client;
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Contract {" +
                "id=" + id +
                ", purchase_date='" + purchase_date + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", is_active='" + is_active + '\'' +
                ", tariff_id='" + tariff.getId() + '\'' +
                ", client_id='" + client.getId() + '\'' +
                ", employee_id='" + employee.getId() + '\'' +
                '}' + "\n";
    }
}
