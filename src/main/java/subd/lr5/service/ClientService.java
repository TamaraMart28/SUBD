package subd.lr5.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.Client;

import java.util.List;
import java.util.Scanner;

public class ClientService {
    public void ClientMenu(SessionFactory sf) {
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
            System.out.println("Input Address:");
            String address = scanner.next();
            System.out.println("Input Email:");
            String email  = scanner.next();
            System.out.println("Input Status of privilege: 1 - true, 0 - false");
            int privilegeInt = scanner.nextInt();
            Boolean privilege = true;
            if (privilegeInt == 0) privilege = false;
            Client client = new Client(full_name, address, email, privilege);
            session.save(client);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Read(Session session) {
        List<Client> clients = session.createQuery("SELECT c from Client c", Client.class).getResultList();
        System.out.println(clients);
    }

    private void Update(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of client:");
            int id = scanner.nextInt();
            System.out.println("Input Full_Name:");
            String full_name = scanner.next();
            System.out.println("Input Address:");
            String address = scanner.next();
            System.out.println("Input Email:");
            String email  = scanner.next();
            System.out.println("Input Status of privilege: 1 - true, 0 - false");
            int privilegeInt = scanner.nextInt();
            Boolean privilege = true;
            if (privilegeInt == 0) privilege = false;
            Client client = session.get(Client.class, id);
            client.setFull_name(full_name);
            client.setAddress(address);
            client.setEmail(email);
            client.setPrivilege(privilege);
            session.save(client);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }

    private void Delete(Session session) {
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Input id of client:");
            int id = scanner.nextInt();
            Client client = session.get(Client.class, id);
            session.delete(client);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    private void Filter(Session session) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input full_name of client:");
            String full_name = scanner.next();
            List<Client> clients = session.createQuery("SELECT c from Client c WHERE full_name = \'" + full_name + "\'", Client.class).getResultList();
            System.out.println(clients);
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }
}
