package com.openwebinars.hibernate.PrimerExemple;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {
	public static void main(String[] args) {
		//Configuration conf = new Configuration().configure();
        //conf.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
        //StandardServiceRegistryBuilder reg = new StandardServiceRegistryBuilder();
        //reg.applySettings(conf.getProperties());
        //ServiceRegistry serviceRegistry = reg.build();
        //SessionFactory sf = conf.buildSessionFactory(); 
        //SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		//sF = conf.buildSessionFactory(serviceRegistry);
		// apply configuration property settings to StandardServiceRegistryBuilder
		//StandardServiceRegistryBuilder reg = new StandardServiceRegistryBuilder();
		//reg.applySettings(conf.getProperties());
		//ServiceRegistry ssr =reg.build();
		//SessionFactory sf = conf.buildSessionFactory(ssr);

		// StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		// SessionFactory sf = new
		// MetadataSources(ssr).buildMetadata(ssr).buildSessionFactory();

		SessionFactory sf = new Configuration().configure().buildSessionFactory();
        //SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session s = sf.openSession();
		Usuari u = new Usuari();
		u.setNum(1);
		u.setNom("Pep");
		u.setMissatge("Hola, Pep");

		Usuari u2 = new Usuari();
		u2.setNum(2);
		u2.setNom("Joan");
		u2.setMissatge("Hola, Joan");

		s.getTransaction().begin();

		s.save(u);
		s.save(u2);

		s.getTransaction().commit();

		s.close();
		sf.close();
		// ssr.close();
	}
}
