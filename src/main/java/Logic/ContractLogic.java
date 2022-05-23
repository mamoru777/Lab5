package Logic;
import Models.Contract;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import Models.Apartment;
import org.postgresql.util.LruCache;
import java.util.Date;

import java.util.List;
import java.util.Scanner;

public class ContractLogic
{
    public void ContractMenu(SessionFactory sf)
    {
        System.out.println("Input a number to choose the action:"
                + "\n1) Create" + "\n2) Read" + "\n3) Update" + "\n4) Delete" + "\n5) Filter");
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        Session session = null;
        session = sf.getCurrentSession();
        session.beginTransaction();

        try
        {
            switch (input)
            {
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
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
        private void Create(Session session)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input Start_data:");
                String st = scanner.next();
                java.sql.Date sql_start_date = java.sql.Date.valueOf(st);
                System.out.println("Input Final_data:");
                String fd = scanner.next();
                java.sql.Date sql_final_date = java.sql.Date.valueOf(fd);
                System.out.println("Input id of apartment");
                int apartmentid = scanner.nextInt();

                Contract contract = new Contract(sql_start_date, sql_final_date, session.get(Apartment.class, apartmentid));
                session.save(contract);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }


        private void Read(Session session)
        {
        List<Contract> contracts = session.createQuery("SELECT c from contract c", Contract.class).getResultList();
        System.out.println(contracts);
    }

        private void Update(Session session)
        {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of contract:");
            int id = scanner.nextInt();
            scanner = new Scanner(System.in);
            System.out.println("Input Start_Date:");
            String st = scanner.next();
            java.sql.Date sql_start_date = java.sql.Date.valueOf(st);
            System.out.println("Input Final_data:");
            String fd = scanner.next();
            java.sql.Date sql_final_date = java.sql.Date.valueOf(fd);
            System.out.println("Input id of apartment");
            int apartmentid = scanner.nextInt();

            Contract contract = session.get(Contract.class, id);
            contract.setStart_data(sql_start_date);
            contract.setFinal_data(sql_final_date);
            contract.setApartment(session.get(Apartment.class, apartmentid));
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

        private void Delete(Session session)
        {
            try
            {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Input id of contract:");
                int id = scanner.nextInt();
                Contract contract = session.get(Contract.class, id);
                session.delete(contract);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        private void Filter(Session session)
        {
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input start_date of contract:");
            String st = scanner.next();
            java.sql.Date sql_start_date = java.sql.Date.valueOf(st);
            List<Contract> contracts = session.createQuery("SELECT c from contract c WHERE start_data = " + sql_start_date , Contract.class).getResultList();
            System.out.println(contracts);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
