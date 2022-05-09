package subd.lr5.service;

import org.hibernate.SessionFactory;
import subd.lr5.models.Tariff;
import org.hibernate.Session;
import java.util.Scanner;
import java.util.List;

public class TariffService {
    public void TariffMenu(SessionFactory sf) {
        System.out.println("Input a number to choose the action:"
                + "\n1) Create" + "\n2) Read" + "\n3) Update" + "\n4) Delete" + "\n5) Filter");
        Scanner scn = new Scanner(System.in);
        int input = scn.nextInt();

        Session session = null;
        session = sf.getCurrentSession();
        session.beginTransaction();

        switch (input){
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
    }

    private void Create(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input Name:");
            String name = scanner.next();
            System.out.println("Input In_City_Call_Cost:");
            Double in_city_call_cost  = scanner.nextDouble();
            System.out.println("Input Out_City_Call_Cost:");
            Double out_city_call_cost  = scanner.nextDouble();
            System.out.println("Input Status of activity: 1 - true, 0 - false");
            int active = scanner.nextInt();
            Boolean is_active = true;
            if (active == 0) is_active = false;
            Tariff tariff = new Tariff(name, in_city_call_cost, out_city_call_cost, is_active);
            session.save(tariff);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Tariff> tariffs = session.createQuery("SELECT t from Tariff t", Tariff.class).getResultList();
        System.out.println(tariffs);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of tariff:");
            int id = scanner.nextInt();
            System.out.println("Input Name:");
            String name = scanner.next();
            System.out.println("Input In_City_Call_Cost:");
            Double in_city_call_cost  = scanner.nextDouble();
            System.out.println("Input Out_City_Call_Cost:");
            Double out_city_call_cost  = scanner.nextDouble();
            System.out.println("Input Status of activity: 1 - true, 0 - false");
            int active = scanner.nextInt();
            Boolean is_active = true;
            if (active == 0) is_active = false;
            Tariff tariff = session.get(Tariff.class, id);
            tariff.setName(name);
            tariff.setIn_city_call_cost(in_city_call_cost);
            tariff.setOut_city_call_cost(out_city_call_cost);
            tariff.setIs_active(is_active);
            session.save(tariff);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of tariff:");
            int id = scanner.nextInt();
            Tariff tariff = session.get(Tariff.class, id);
            session.delete(tariff);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input name of tariff:");
            String name = scanner.next();
            List<Tariff> tariffs = session.createQuery("SELECT t from Tariff t WHERE name = \'" + name + "\'", Tariff.class).getResultList();
            System.out.println(tariffs);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
