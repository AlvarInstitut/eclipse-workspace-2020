package exemples

import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	
	val t = sessio.beginTransaction()
	
	val files = sessio.createQuery("delete Institut where codi like '12%'").executeUpdate()
         
    println("S'han esborrat " + files + " files.")

	t.rollback()
	
	sessio.close()
}