package com.openwebinars.hibernate.PrimerExempleHibernateJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrimerExempleHibernateJPA");

         //Generamos un EntityManager
         EntityManager em = emf.createEntityManager();

         //Iniciamos una transacción
         em.getTransaction().begin();

         // Construimos un objeto de tipo User
         Usuari user1 = new Usuari();
         user1.setNum(1);
         user1.setNom("Pepe");
         user1.setMissatge("Hello world from JPA with Pepe");
         System.out.println("Primer usuari");

         // Construimos otro objeto de tipo User
         Usuari user2 = new Usuari();
         user2.setNum(2);
         user2.setNom("Juan");
         user2.setMissatge("Hello world from JPA with Juan");
         System.out.println("Primer usuari");

         //Persistimos los objetos
         em.persist(user1);
         em.persist(user2);

         //Commiteamos la transacción
         em.getTransaction().commit();

         //Cerramos el EntityManager
         em.close();
    }
}
