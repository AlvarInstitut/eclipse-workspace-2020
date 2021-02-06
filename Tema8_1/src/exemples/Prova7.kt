package exemples

import redis.clients.jedis.Jedis

fun main(){
        val con = Jedis("localhost")
        con.connect()
        
        val subcamps = con.hkeys("empleat_1")  // sucamps és un MutableSet
        for (subcamp in subcamps)
            System.out.println(subcamp + ": " + con.hget("empleat_1", subcamp))
        
        con.close()
}