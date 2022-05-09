package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.*;

import java.util.List;
import java.util.Scanner;

public class ContractService {
    public void ContractMenu(SessionFactory sf) {
        System.out.println("Input a number to choose the action:"
                + "\n1) Create" + "\n2) Read" + "\n3) Update" + "\n4) Delete" + "\n5) Filter");
        Scanner scn = new Scanner(System.in);
        int input = scn.nextInt();

        Session session = null;
        session = sf.getCurrentSession();
        session.beginTransaction();

        try {
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
        catch (Exception ex){
            System.out.println(ex);
        }

    }


    private void Create(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input Purchase_Date:");
            String pd = scanner.next();
            java.sql.Date sql_purchase_date = java.sql.Date.valueOf(pd);
            System.out.println("Input Phone_Number:");
            String phone_number = scanner.next();
            System.out.println("Input Status of activity: 1 - true, 0 - false");
            int active = scanner.nextInt();
            Boolean is_active = true;
            if (active == 0) is_active = false;

            System.out.println("Input id of tariff");
            int tariffid = scanner.nextInt();
            System.out.println("Input id of client");
            int clientid = scanner.nextInt();
            System.out.println("Input id of employee");
            int employeeid = scanner.nextInt();

            Contract contract = new Contract(sql_purchase_date, phone_number, is_active,
                    session.get(Tariff.class, tariffid), session.get(Client.class, clientid), session.get(Employee.class, employeeid));
            session.save(contract);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Contract> contracts = session.createQuery("SELECT c from Contract c", Contract.class).getResultList();
        System.out.println(contracts);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of contract:");
            int id = scanner.nextInt();
            System.out.println("Input Purchase_Date:");
            String pd = scanner.next();
            java.sql.Date sql_purchase_date = java.sql.Date.valueOf(pd);
            System.out.println("Input Phone_Number:");
            String phone_number = scanner.next();
            System.out.println("Input Status of activity: 1 - true, 0 - false");
            int active = scanner.nextInt();
            Boolean is_active = true;
            if (active == 0) is_active = false;

            System.out.println("Input id of tariff");
            int tariffid = scanner.nextInt();
            System.out.println("Input id of client");
            int clientid = scanner.nextInt();
            System.out.println("Input id of employee");
            int employeeid = scanner.nextInt();

            Contract contract = session.get(Contract.class, id);
            contract.setPurchase_date(sql_purchase_date);
            contract.setPhone_number(phone_number);
            contract.setIs_active(is_active);
            contract.setTariff(session.get(Tariff.class, tariffid));
            contract.setClient(session.get(Client.class, clientid));
            contract.setEmployee(session.get(Employee.class, employeeid));
            session.save(contract);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of contract:");
            int id = scanner.nextInt();
            Contract contract = session.get(Contract.class, id);
            session.delete(contract);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input tariff_id of contract:");
            int tariffid = scanner.nextInt();
            List<Contract> contracts = session.createQuery("SELECT c from Contract c WHERE tariffid = " + tariffid , Contract.class).getResultList();
            System.out.println(contracts);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
