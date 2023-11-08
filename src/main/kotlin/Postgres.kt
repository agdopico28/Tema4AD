import java.sql.CallableStatement
import java.sql.DriverManager
import java.sql.Types

/*fun main() {
    Class.forName("org.postgresql.Driver")
    val url = "jdbc:postgresql://172.17.0.2:5432/postgres"
    val usuario = "postgres"
    val password = "secret"
    val con = DriverManager.getConnection(url, usuario, password)

    val cStmt = con.prepareCall("{call CantidadPersonas(?)}")
    cStmt.registerOutParameter(1, Types.INTEGER)
    cStmt.setString(1,"Juan")
    cStmt.execute()
    val resultado = cStmt.getInt(1)
    println("Resultado: $resultado")
    con.close()
}*/

//fun main() {
//    Class.forName("org.postgresql.Driver")
//    val url = "jdbc:postgresql://172.17.0.2:5432/postgres"
//    val usuario = "postgres"
//    val password = "secret"
//    val con = DriverManager.getConnection(url, usuario, password)
//
//    val cStmt = con.createStatement()
//    val consulta = "select CantidadTotalPersonas()"
//
//    val resultado = cStmt.executeQuery(consulta)
//    resultado.next()
//    println("Resultado: ${resultado.getInt(1)}")
//    con.close()
//}

fun main() {
    Class.forName("org.postgresql.Driver")
    val url = "jdbc:postgresql://172.17.0.2:5432/postgres"
    val usuario = "postgres"
    val password = "secret"
    val con = DriverManager.getConnection(url, usuario, password)

    val pStmt = con.prepareStatement("select ListaPersonas(?)")
    pStmt.setString(1, "R%")
    val resultado = pStmt.executeQuery()
    while (resultado.next()){
        println("Detalles: ${resultado.getString(1)}")
    }
    resultado.close()
    con.close()
}