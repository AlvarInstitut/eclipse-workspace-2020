package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args:Array<String>){
	val sF = SessionFactoryUtil.sessionFactory  // fem !! assumint que sempre ens donarà el sessionFactory
	val sessio = sF.openSession()
	val com = sessio.load("classes.Comarca","Alt Maestrat") as Comarca
	print("La comarca " + com.nomC) 
	print(" (província de " + com.provincia + ") ")
	println("té " + com.poblacios?.size + " pobles")
	sessio.close()
}

