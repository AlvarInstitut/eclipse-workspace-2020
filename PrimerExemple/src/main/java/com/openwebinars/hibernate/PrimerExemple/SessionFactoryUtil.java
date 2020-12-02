package com.openwebinars.hibernate.PrimerExemple;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

 
public class SessionFactoryUtil {
 
    private static SessionFactory sF;
 
    public static SessionFactory getSessionFactory() {
        if (sF == null) {
        	Configuration cfg=new Configuration().configure();
        	StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(
        	            cfg.getProperties());
        	sF= cfg.buildSessionFactory(builder.build());   
        }
         
        return sF;
    }
}