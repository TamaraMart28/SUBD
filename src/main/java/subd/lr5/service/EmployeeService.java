package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    public void EmployeeMenu(SessionFactory sf) {
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
            System.out.println("Input Full_Name:");
            String full_name = scanner.next();
            System.out.println("Input Work_Place:");
            String work_place  = scanner.next();
            System.out.println("Input Position :");
            String position  = scanner.next();
            Employee employee = new Employee(full_name, work_place, position);
            session.save(employee);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Employee> employees = session.createQuery("SELECT e from Employee e", Employee.class).getResultList();
        System.out.println(employees);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of employee:");
            int id = scanner.nextInt();
            System.out.println("Input Full_Name:");
            String full_name = scanner.next();
            System.out.println("Input Work_Place:");
            String work_place  = scanner.next();
            System.out.println("Input Position :");
            String position  = scanner.next();
            Employee employee = session.get(Employee.class, id);
            employee.setFull_name(full_name);
            employee.setWork_place(work_place);
            employee.setPosition(position);
            session.save(employee);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of employee:");
            int id = scanner.nextInt();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input work_place of employee:");
            String work_place = scanner.next();
            List<Employee> employees = session.createQuery("SELECT e from Employee e WHERE work_place = \'" + work_place + "\'", Employee.class).getResultList();
            System.out.println(employees);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
