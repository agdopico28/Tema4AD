import java.sql.DriverManager

fun main(args: Array<String>) {
    CreateTable()
}

fun CreateTable(){
    val url = "jdbc:mysql://172.17.0.2:3306/GESTION"
    val usuari = "root"
    val password = "secret"


    val con = DriverManager.getConnection(url, usuari, password)

    val st = con.createStatement ()

    val sentSQL = "CREATE TABLE USUARIO(" +
            "id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
            "nombre varchar(15), " +
            "user varchar(10), " +
            "password VARCHAR(20), " +
            "tlf integer ," +
            "email varchar(30) unique " +
            ");"

    st.executeUpdate(sentSQL)
    st.close();
    con.close()
}
