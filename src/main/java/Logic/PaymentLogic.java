package Logic;
import Models.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Models.Apartment;
import Models.Contract;
import org.postgresql.util.LruCache;

import java.util.List;
import java.util.Scanner;

public class PaymentLogic
{
    public void PaymentMenu(SessionFactory sf)
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
            System.out.println("Input Data:");
            String dt = scanner.next();
            java.sql.Date data = java.sql.Date.valueOf(dt);
            System.out.println("Input Cost:");
            int cost = scanner.nextInt();
            System.out.println("Input Arrear:");
            int arrear = scanner.nextInt();
            System.out.println("Input Penny:");
            int penny = scanner.nextInt();
            System.out.println("Input Payment:");
            int pt = scanner.nextInt();
            System.out.println("Input id of Contract");
            int contractid = scanner.nextInt();
            Payment payment = new Payment(data, cost, arrear, penny, pt, session.get(Contract.class, contractid));
            session.save(payment);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Payment> payments = session.createQuery("SELECT c from payment c", Payment.class).getResultList();
        System.out.println(payments);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of apartment:");
            int id = scanner.nextInt();
            System.out.println("Input Data:");
            String dt = scanner.next();
            java.sql.Date data = java.sql.Date.valueOf(dt);
            System.out.println("Input Cost:");
            int cost = scanner.nextInt();
            System.out.println("Input Arrear:");
            int arrear = scanner.nextInt();
            System.out.println("Input Penny:");
            int penny = scanner.nextInt();
            System.out.println("Input Payment:");
            int pt = scanner.nextInt();
            System.out.println("Input id of Contract");
            int contractid = scanner.nextInt();
            Payment payment = session.get(Payment.class, id);
            payment.setData(data);
            payment.setCost(cost);
            payment.setArrear(arrear);
            payment.setPenny(penny);
            payment.setPayment(pt);
            payment.setContract(session.get(Contract.class, contractid));
            session.save(payment);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of payment:");
            int id = scanner.nextInt();
            Payment payment = session.get(Payment.class, id);
            session.delete(payment);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void Filter(Session session)
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input cost of apartment:");
            int cost = scanner.nextInt();
            List<Payment> payments = session.createQuery("SELECT c from payment c WHERE cost = " + cost, Payment.class).getResultList();
            System.out.println(payments);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
