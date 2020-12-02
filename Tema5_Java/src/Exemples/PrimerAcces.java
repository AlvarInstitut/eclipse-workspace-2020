package Exemples;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import classes.Comarca;

public class PrimerAcces {

    public static void main(String[] args) {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        Session sessio = sf.openSession();
        Comarca com = (Comarca) sessio.load(Comarca.class, "Alt Maestrat");
        System.out.print("Comarca " + com.getNomC() + ": ");
        System.out.print(com.getProvincia());
        System.out.println(" (" + com.getPoblacios().size() + " pobles)");
        sessio.close();
    }
}