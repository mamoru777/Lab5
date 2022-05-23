import Logic.RequestLogic;
import Logic.ApartmentLogic;
import Logic.ContractLogic;
import Logic.PaymentLogic;
import Logic.ServiceLogic;
import Logic.Contract_ServiceLogic;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;
import Models.Apartment;
import Models.Contract;
import Models.Payment;
import Models.Service;
import Models.Contract_Service;

public class Main
{
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Apartment.class)
                .addAnnotatedClass(Contract.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Service.class)
                .addAnnotatedClass(Contract_Service.class)
                .buildSessionFactory();

        boolean isMenu = true;
        while(isMenu) {
            try {
                System.out.println("\n\nInput a number to work with:"
                        + "\n1) Apartments" + "\n2) Contracts" + "\n3) Payments" + "\n4) Services" + "\n5) Contract_Services 6) Request"
                        + "\nInput 0 to finish");
                Scanner scn = new Scanner(System.in);
                int input = scn.nextInt();
                switch (input) {
                    case 0:
                        isMenu = false;
                        break;
                    case 1:
                        ApartmentLogic ts = new ApartmentLogic();
                        try {
                            ts.ApartmentMenu(sessionFactory);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            ts.ApartmentMenu(sessionFactory);
                        }
                        break;
                    case 2:
                        ContractLogic es = new ContractLogic();
                        try {
                            es.ContractMenu(sessionFactory);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            es.ContractMenu(sessionFactory);
                        }

                        break;
                    case 3:
                        PaymentLogic cls = new PaymentLogic();
                        try {
                            cls.PaymentMenu(sessionFactory);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            cls.PaymentMenu(sessionFactory);
                        }

                        break;
                    case 4:
                        ServiceLogic cs = new ServiceLogic();
                        try {
                            cs.ServiceMenu(sessionFactory);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            cs.ServiceMenu(sessionFactory);
                        }
                        break;
                    case 5:
                        Contract_ServiceLogic exs = new Contract_ServiceLogic();
                        try {
                            exs.Contract_ServiceMenu(sessionFactory);
                        } catch (Exception ex) {
                            System.out.println(ex);
                            exs.Contract_ServiceMenu(sessionFactory);
                        }
                        break;
                    case 6:
                        RequestLogic rl = new RequestLogic();
                        rl.work(sessionFactory);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
            sessionFactory.close();
    }
}
