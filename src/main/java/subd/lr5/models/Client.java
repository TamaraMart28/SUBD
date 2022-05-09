package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "client", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "address")
    private String address ;

    @Column(name = "email")
    private String email ;

    @Column(name = "privilege ")
    private Boolean privilege ;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts;

    public Client() {}

    public Client(String full_name, String address, String email, Boolean privilege) {
        this.full_name = full_name;
        this.address = address;
        this.email = email;
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "Client {" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", privilege='" + privilege + '\'' +
                '}' + "\n";
    }
}
