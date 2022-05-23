package Models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity(name = "apartment")
@Table(schema = "public", catalog = "test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Apartment
{
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serial1")
    @SequenceGenerator(name="serial1", sequenceName = "serial1", allocationSize = 1)
    @Id
    @Column(name = "apartmentid")
    private int id;
    @Column(name = "flat_number")
    private int flat_number;
    @Column(name = "number_of_people")
    private int number_of_people;
    @Column(name = "square_metres")
    private int square_metres;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "apartment", cascade = CascadeType.MERGE)
    private List<Contract> contracts;

    public Apartment(int flat_number, int number_of_people, int square_metres)
    {
        this.flat_number = flat_number;
        this.number_of_people = number_of_people;
        this.square_metres = square_metres;
    }
        @Override
        public String toString() {
            return "Apartment {" +
                    "apartmentid=" + id +
                    ", flat_number=" + flat_number + '\'' +
                    ", number_of_people=" + number_of_people + '\'' +
                    ", square_metres=" + square_metres + '\'' +
                    '}' + "\n";
    }
}
