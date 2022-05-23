package Logic;
import Models.Service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class ServiceLogic
{
    public void ServiceMenu(SessionFactory sf)
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
            System.out.println("Input Name:");
            String name = scanner.next();
            System.out.println("Input Cost_of_square:");
            int cos = scanner.nextInt();
            System.out.println("Input Cost_of_people:");
            int cop = scanner.nextInt();
            Service service = new Service(name, cos, cop);
            session.save(service);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Service> services = session.createQuery("SELECT c from service c", Service.class).getResultList();
        System.out.println(services);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of Service:");
            int id = scanner.nextInt();
            System.out.println("Input Name:");
            String name = scanner.next();
            System.out.println("Input Cost_of_square:");
            int cos = scanner.nextInt();
            System.out.println("Input Cost_of_people:");
            int cop = scanner.nextInt();
            Service service = session.get(Service.class, id);
            service.setName(name);
            service.setCost_of_square(cos);
            service.setCost_of_people(cop);
            session.save(service);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of Service:");
            int id = scanner.nextInt();
            Service service = session.get(Service.class, id);
            session.delete(service);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void Filter(Session session)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input name of Service:");
            int name = scanner.nextInt();
            List<Service> services= session.createQuery("SELECT c from service c WHERE name = " + name, Service.class).getResultList();
            System.out.println(services);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
