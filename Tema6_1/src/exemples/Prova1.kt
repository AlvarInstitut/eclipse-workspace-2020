package exemples

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

fun main(){


        val url = "jdbc:postgresql://89.36.214.106:5432/r00"

        val con = DriverManager.getConnection(url, "r00", "r00");

        val rs = con.createStatement().executeQuery("select nom,major_edat from persona4 order by nom");

        while (rs.next()) {
            if (rs.getBoolean(2))
                System.out.println(rs.getString(1) + " és major d'edat")
            else
                System.out.println(rs.getString(1) + " és menor d'edat")
        }
        rs.close()
        con.close()
    
}