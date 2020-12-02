package exemples

import classes.SessionFactoryUtil
import classes.Poblacio
import classes.Comarca

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val q = sessio.createQuery("select c.nomC,count(p.codM),avg(p.altura) "
                                        + "from Comarca c , Poblacio p "
                                        + "where c.nomC=p.comarca.nomC "
                                        + "group by c.nomC "
                                        + "order by c.nomC");
    val llista = q.list();
    llista.forEach{
		it as Array<Object>
        println("Comarca: " + it[0] + ". Núm. pobles: " + it[1] + ". Altura mitjana: " + it[2]);
	}
	sessio.close()
}