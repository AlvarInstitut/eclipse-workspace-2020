package dades

import classes.SessionFactoryUtil
import classes.Comarca

fun main(args: Array<String>) {

        val sessio = SessionFactoryUtil.getSessionFactory().openSession()

        val q = sessio.createQuery("from Comarca order by nomC");

        for (com  in q.list() as List<Comarca>){
			//com as Comarca
            System.out.println(com.nomC + " - " + com.provincia)
		}
 
    }