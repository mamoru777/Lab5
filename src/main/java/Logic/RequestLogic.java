package Logic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Models.Apartment;
import java.util.List;
import java.util.Scanner;

public class RequestLogic
{
    public void work (SessionFactory sessionFactory)
    {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Apartment> apartments = session.createQuery("SELECT c FROM apartment c", Apartment.class).getResultList();
        System.out.print("~Apartments~");
        for (int i = 0; i < apartments.size(); i++)
        {
            System.out.format("\nFlat_number: %d, Number_of_people: %d, Square_metre: %d", apartments.get(i).getFlat_number(),
                    apartments.get(i).getNumber_of_people(), apartments.get(i).getSquare_metres());
        }
        session.getTransaction().commit();
    }
}
