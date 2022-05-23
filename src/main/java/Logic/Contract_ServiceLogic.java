package Logic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Models.Apartment;
import Models.Contract;
import Models.Contract_Service;
import Models.Service;

import java.util.List;
import java.util.Scanner;

public class Contract_ServiceLogic
{
    public void Contract_ServiceMenu(SessionFactory sf)
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
            System.out.println("Input ContractId:");
            int cid = scanner.nextInt();
            System.out.println("Input ServiceId:");
            int sid = scanner.nextInt();
            Contract_Service cs = new Contract_Service(session.get(Contract.class, cid), session.get(Service.class, sid));
            session.save(cs);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Contract_Service> css = session.createQuery("SELECT c from contract_service c", Contract_Service.class).getResultList();
        System.out.println(css);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of apartment:");
            int id = scanner.nextInt();
            System.out.println("Input ContractId:");
            int cid = scanner.nextInt();
            System.out.println("Input ServiceId:");
            int sid = scanner.nextInt();
            Contract_Service cs = session.get(Contract_Service.class, id);
            cs.setContract(session.get(Contract.class, cid));
            cs.setService(session.get(Service.class, sid));
            session.save(cs);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of Contract_Service:");
            int id = scanner.nextInt();
            Contract_Service cs = session.get(Contract_Service.class, id);
            session.delete(cs);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void Filter(Session session)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input Contractid of Contract_service:");
            int cid = scanner.nextInt();
            List<Contract_Service> css = session.createQuery("SELECT c from contract_service c WHERE contractid = " + cid, Contract_Service.class).getResultList();
            System.out.println(css);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
