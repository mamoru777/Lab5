package Logic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Models.Apartment;
import org.postgresql.util.LruCache;

import java.util.List;
import java.util.Scanner;

public class ApartmentLogic
{
    public void ApartmentMenu(SessionFactory sf)
    {
        System.out.println("Input a number to choose the action:"
                + "\n1) Create" + "\n2) Read" + "\n3) Update" + "\n4) Delete" + "\n5) Filter");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        Session session = null;
        session = sf.getCurrentSession();
        session.beginTransaction();

        try {
            switch (input) {
                case 1:
                    Create(session);
                    break;
                case 2:
                    Read(session);
                    break;
                case 3:
                    Update(session);
                    break;
                case 4:
                    Delete(session);
                    break;
                case 5:
                    Filter(session);
                    break;
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void Create(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input Flat_number:");
            int flat_number = scanner.nextInt();
            System.out.println("Input Number_of_people:");
            int number_of_people = scanner.nextInt();
            System.out.println("Input Square_metres:");
            int square_metres = scanner.nextInt();
            Apartment apartment = new Apartment(flat_number, number_of_people, square_metres);
            session.save(apartment);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Apartment> apartments = session.createQuery("SELECT a from apartment a", Apartment.class).getResultList();
        System.out.println(apartments);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of apartment:");
            int id = scanner.nextInt();
            System.out.println("Input Flat_number:");
            int flat_number = scanner.nextInt();
            System.out.println("Input Number_of_people:");
            int number_of_people = scanner.nextInt();
            System.out.println("Input Square_metres:");
            int square_metres = scanner.nextInt();
            Apartment apartment = session.get(Apartment.class, id);
            apartment.setFlat_number(flat_number);
            apartment.setNumber_of_people(number_of_people);
            apartment.setSquare_metres(square_metres);
            session.save(apartment);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of apartment:");
            int id = scanner.nextInt();
            Apartment apartment = session.get(Apartment.class, id);
            session.delete(apartment);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void Filter(Session session)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input number_of_people of apartment:");
            int number_of_people = scanner.nextInt();
            List<Apartment> apartments = session.createQuery("SELECT c from apartment c WHERE number_of_people = " + number_of_people, Apartment.class).getResultList();
            System.out.println(apartments);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
