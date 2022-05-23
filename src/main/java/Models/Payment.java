package Models;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity(name = "payment")
@Table(schema = "public", catalog = "test")
//@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial3")
    @SequenceGenerator(name="serial3", sequenceName = "serial3", allocationSize = 1)
    @Id
    @Column(name = "paymentid")
    private int id;
    @Column(name = "data")
    private Date data;
    @Column(name = "cost")
    private int cost;
    @Column(name = "arrear")
    private int arrear;
    @Column(name = "penny")
    private int penny;
    @Column(name = "payment")
    private int payment;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contractid", nullable = true)
    private Contract contract;

    public Payment(Date data, int cost, int arrear, int penny, int payment, Contract contract)
    {
        this.data = data;
        this.cost = cost;
        this.arrear = arrear;
        this.penny = penny;
        this.payment = payment;
        if (contract == null)
        {
            this.contract = null;
        }
        else
        {
            this.contract = contract;
        }

    }
    public long getId(){return id;}
    public Date getData(){return data;}
    public int getCost(){return cost;}
    public int getArrear(){return arrear;}
    public int getPenny(){return penny;}
    public int getPayment(){return payment;}
    public Contract getContract()
    {
        if (contract == null)
        return null;
        else return contract;
    }

    @Override
    public String toString()
    {
        if (contract != null)
        {
            return "Payment {" +
                    "id=" + id +
                    ", data=" + data + '\'' +
                    ", cost=" + cost + '\'' +
                    ", arrear=" + arrear + '\'' +
                    ", penny=" + penny + '\'' +
                    ", payment=" + payment + '\'' +
                    ", contractid=" + contract.getId() + '\'' +
                    '}' + "\n";
        }
        else
        {
            return "Payment {" +
                    "id=" + id +
                    ", data=" + data + '\'' +
                    ", cost=" + cost + '\'' +
                    ", arrear=" + arrear + '\'' +
                    ", penny=" + penny + '\'' +
                    ", payment=" + payment + '\'' +
                    ", contractid=" + null + '\'' +
                    '}' + "\n";
        }
    }
}
