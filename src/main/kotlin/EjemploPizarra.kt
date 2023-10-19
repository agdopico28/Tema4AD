import java.sql.DriverManager
//Amalia

fun main(args: Array<String>) {
   Delete()
}

fun Create(){
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)

    val st = con.createStatement ()

    val sentSQL = "CREATE TABLE USUARIO(" +
            "id INTEGER CONSTRAINT cp_id PRIMARY KEY, " +
            "nombre varchar(15), " +
            "user varchar(10), " +
            "password integer, " +
            "tlf integer ," +
            "email varchar(30)" +
            ")"

    st.executeUpdate(sentSQL)
    st.close();
    con.close()
}

fun Insert(){
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)

    val st = con.createStatement ()

    st.executeUpdate("INSERT INTO USUARIO VALUES (1, 'Amalia', 'ama', 123, 698666889, 'ama@gmail.com')")

    st.executeUpdate("INSERT INTO USUARIO VALUES (2,'Borja','bor',28,604684064, 'bor@gmail.com')")


    st.close()
    con.close()
}

fun Select(){
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)
    val st = con.createStatement()
    val rs = st.executeQuery("SELECT * FROM USUARIO")
    while (rs.next()) {
        print("" + rs.getInt(1) + "\t")
        println(rs.getString(2))
        println(rs.getString(3))
        println(rs.getInt(4))
        println(rs.getInt(5))
        println(rs.getString(6))

    }
    st.close()
    con.close()
}

fun Update(){
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)
    val st = con.createStatement()

    st.executeUpdate("UPDATE USUARIO SET password = 1234 where id = 2")

    st.close()
    con.close()
}


fun Delete(){
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)
    val st = con.createStatement()

    st.executeUpdate("DELETE FROM USUARIO WHERE password = 1234")

    st.close()
    con.close()
}