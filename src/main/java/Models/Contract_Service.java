package Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "contract_service")
@Table(schema = "public", catalog = "test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract_Service
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial4")
    @SequenceGenerator(name="serial4", sequenceName = "serial4", allocationSize = 1)
    @Id
    @Column(name = "contract_serviceid")
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractid")
    private Contract contract;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "serviceid")
    private Service service;

    public Contract_Service(Contract contract, Service service)
    {
        this.contract = contract;
        this.service = service;
    }

    @Override
    public String toString() {
        return "Contract_Service {" +
                "id=" + id +
                ", contract=" + contract.getId() + '\'' +
                ", Services=" + service.getId() + '\'' +
                '}' + "\n";
    }
}
