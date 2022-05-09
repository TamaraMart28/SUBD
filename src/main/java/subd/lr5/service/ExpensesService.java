package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.*;

import java.util.List;
import java.util.Scanner;

public class ExpensesService {
    public void ExpensesMenu(SessionFactory sf) {
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
            System.out.println("Input Date:");
            String dt = scanner.next();
            java.sql.Date sql_date = java.sql.Date.valueOf(dt);
            System.out.println("Input In_City_Call_Minutes:");
            int in_city_call_minutes = scanner.nextInt();
            System.out.println("Input Out_City_Call_Minutes:");
            int out_city_call_minutes = scanner.nextInt();

            System.out.println("Input id of contract");
            int contractid = scanner.nextInt();

            Expenses expenses = new Expenses(sql_date, in_city_call_minutes, out_city_call_minutes, session.get(Contract.class, contractid));
            session.save(expenses);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Expenses> expenses = session.createQuery("SELECT e from Expenses e", Expenses.class).getResultList();
        System.out.println(expenses);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of expenses:");
            int id = scanner.nextInt();
            System.out.println("Input Date:");
            String dt = scanner.next();
            java.sql.Date sql_date = java.sql.Date.valueOf(dt);
            System.out.println("Input In_City_Call_Minutes:");
            int in_city_call_minutes = scanner.nextInt();
            System.out.println("Input Out_City_Call_Minutes:");
            int out_city_call_minutes = scanner.nextInt();

            System.out.println("Input id of contract");
            int contractid = scanner.nextInt();

            Expenses expenses = session.get(Expenses.class, id);
            expenses.setDate(sql_date);
            expenses.setIn_city_call_minutes(in_city_call_minutes);
            expenses.setOut_city_call_minutes(out_city_call_minutes);
            expenses.setContract(session.get(Contract.class, contractid));
            session.save(expenses);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of expenses:");
            int id = scanner.nextInt();
            Expenses expenses = session.get(Expenses.class, id);
            session.delete(expenses);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input contract_id of expenses:");
            int contractid = scanner.nextInt();
            List<Expenses> expenses = session.createQuery("SELECT e from Expenses e WHERE contractid = " + contractid , Expenses.class).getResultList();
            System.out.println(expenses);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
