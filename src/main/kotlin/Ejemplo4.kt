import java.sql.DriverManager
//Amalia

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:ruta_del_fitxer_sqlite"

    val	con = DriverManager.getConnection(url)

    val st = con.createStatement ()
    st.executeUpdate("create TABLE T2 (c1 TEXT)")
    st.close()

    con.close()
}