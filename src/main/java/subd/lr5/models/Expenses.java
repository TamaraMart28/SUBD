package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;

@Entity
@Table(name = "expenses", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "in_city_call_minutes")
    private int in_city_call_minutes;

    @Column(name = "out_city_call_minutes")
    private int out_city_call_minutes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractid")
    private Contract contract;

    public Expenses() {}

    public Expenses(Date date, int in_city_call_minutes, int out_city_call_minutes, Contract contract) {
        this.date = date;
        this.in_city_call_minutes = in_city_call_minutes;
        this.out_city_call_minutes = out_city_call_minutes;
        this.contract = contract;
    }

    @Override
    public String toString() {
        return "Expenses {" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", in_city_call_minutes='" + in_city_call_minutes + '\'' +
                ", out_city_call_minutes='" + out_city_call_minutes + '\'' +
                ", contract_id='" + contract.getId() + '\'' +
                '}' + "\n";
    }
}
