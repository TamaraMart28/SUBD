package subd.lr5;

import subd.lr5.models.*;
import subd.lr5.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Tariff.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Contract.class)
                .addAnnotatedClass(Expenses.class)
                .buildSessionFactory();

        boolean isMenu = true;
        while(isMenu){
            try {
                ZaprosService zs = new ZaprosService();
                zs.work(sessionFactory);
                System.out.println("\n\nInput a number to work with:"
                        + "\n1) Tariffs" + "\n2) Employees" + "\n3) Clients" + "\n4) Contracts" + "\n5) Expenses 6)Zapros"
                        + "\nInput 0 to finish");

                Scanner scn = new Scanner(System.in);
                int input = scn.nextInt();

                switch (input){
                    case 0:
                        isMenu = false;
                        break;
                    case 1:
                        TariffService ts = new TariffService();
                        try {
                            ts.TariffMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            ts.TariffMenu(sessionFactory);
                        }
                        break;
                    case 2:
                        EmployeeService es = new EmployeeService();
                        try {
                            es.EmployeeMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            es.EmployeeMenu(sessionFactory);
                        }

                        break;
                    case 3:
                        ClientService cls = new ClientService();
                        try {
                            cls.ClientMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            cls.ClientMenu(sessionFactory);
                        }

                        break;
                    case 4:
                        ContractService cs = new ContractService();
                        try {
                            cs.ContractMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            cs.ContractMenu(sessionFactory);
                        }

                        break;
                    case 5:
                        ExpensesService exs = new ExpensesService();
                        try {
                            exs.ExpensesMenu(sessionFactory);
                        }
                        catch (Exception ex){
                            System.out.println(ex);
                            exs.ExpensesMenu(sessionFactory);
                        }

                        break;
                }
            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }
        sessionFactory.close();
    }
}
