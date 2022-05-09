package subd.lr5.service;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import subd.lr5.models.*;

import java.util.List;

public class ZaprosService {
    public void work (SessionFactory sessionFactory) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Client> clients = session.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        System.out.print("~Clients~");
        for (int i = 0; i < clients.size(); i++) {
            System.out.format("\nFull name: %s, Address: %s, Email: %s, Satus of privilege: %s;", clients.get(i).getFull_name(),
                    clients.get(i).getAddress(), clients.get(i).getEmail(), clients.get(i).getPrivilege());
        }
        session.getTransaction().commit();
    }
}
