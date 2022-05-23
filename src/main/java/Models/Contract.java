package Models;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "contract")
@Table(schema = "public", catalog = "test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial2")
    @SequenceGenerator(name="serial2", sequenceName = "serial2", allocationSize = 1)
    @Id
    @Column(name = "contractid")
    private int id;
    @Column(name = "start_data")
    private Date start_data;
    @Column(name = "final_data")
    private Date final_data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartmentid")
    private Apartment apartment;
    @OneToMany(mappedBy = "contract")
    private List<Payment> payments;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "contract", cascade = CascadeType.MERGE)
    private List<Contract_Service> contract_services;

    public Contract(Date start_data, Date final_data, Apartment apartment)
    {
        this.start_data = start_data;
        this.final_data = final_data;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Contract {" +
                "id=" + id +
                ", start_data=" + start_data + '\'' +
                ", final_data=" + final_data + '\'' +
                ", apartmentid=" + apartment.getId() + '\'' +
                '}' + "\n";
    }
}
