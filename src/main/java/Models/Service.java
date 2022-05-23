package Models;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name = "service")
@Table(schema = "public", catalog = "test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial5")
    @SequenceGenerator(name="serial5", sequenceName = "serial5", allocationSize = 1)
    @Id
    @Column(name = "serviceid")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost_of_square")
    private int cost_of_square;
    @Column(name = "cost_of_people")
    private int cost_of_people;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "contract", cascade = CascadeType.MERGE)
    private List<Contract_Service> contract_service;

    public Service(String name, int cost_of_square, int cost_of_people)
    {
        this.name = name;
        this.cost_of_square = cost_of_square;
        this.cost_of_people = cost_of_people;
    }

    @Override
    public String toString() {
        return "Service {" +
                "id=" + id +
                ", name=" + name + '\'' +
                ", cost_of_square=" + cost_of_square + '\'' +
                ", cost_of_people=" + cost_of_people + '\'' +
                '}' + "\n";
    }
}
