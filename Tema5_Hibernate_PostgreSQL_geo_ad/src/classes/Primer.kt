package classes

import org.hibernate.cfg.Configuration
import classes.Comarca

fun main(args:Array<String>){
	
        val sf = Configuration().configure().buildSessionFactory()
        val sessio = sf.openSession()
        val com = sessio.load("classes.Comarca", "Alt Maestrat") as Comarca
        print("Comarcalització " + com.nomC + ": ")
        print(com.provincia)
        println(" (" + com.poblacios?.size + " pobles)")
        sessio.close();
}
