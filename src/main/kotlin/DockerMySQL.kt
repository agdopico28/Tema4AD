import java.io.BufferedReader
import java.io.InputStreamReader
import java.sql.Connection
import java.sql.DriverManager
import java.util.*


//Para iniciar el docker hace falta introducir este comando en el terminal: docker start mysql-db

// Primero habrá que crear el "Schema": Prueba25(boton derecho) -> New -> Schema
fun main(args: Array<String>) {
    showMenu()
}

fun showMenu() {
    val s = "Opciones: "

    println(" ")
    println(s)
    println("-".repeat(s.length))
    println("0- Exit")
    println("1- Create")
    println("2- Update")
    println("3- Insert")
    println("4- Delete")
    println("5- Select")
    introduceNumber() //se ejecuta para introducir uno nuevo
}


private fun introduceNumber() {  //metodo para introducir numeros
    println(" ")
    println("Introdueix un numero (0 per acabar): ")
    val input = BufferedReader(InputStreamReader(System.`in`)).readLine()
    val num = input.toIntOrNull()
    if (num != null) {
        loopNumbers(num)


    } else {
        println("Entrada no valida. Introduix un numero válid.")
        println("")
        showMenu()
    }
}


fun loopNumbers(number: Int) {
    when (number) {
        0 -> { //asi se sale
            System.exit(0)

        }
        1 -> {
            CreateTable()
            SelectTable()
            showMenu()
        }
        2 -> {
            UpdateTable()
            SelectTable()
            showMenu()
        }
        3 -> {
            InsertTable()
            SelectTable()
            showMenu()
        }
        4 -> {
            DeleteTable()
            SelectTable()
            showMenu()
        }

        else -> {
            SelectTable()
            showMenu()
        }
    }
}

fun CreateTable(){
    val con = Conexion()

    val st = con.createStatement ()

    val sentSQL = "CREATE TABLE if not exists USUARIO(" +
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

fun DeleteTable(){
    val con = Conexion()

    println("Borra una fila")
    println("Introduce el id:")
    val id = Scanner(System.`in`).nextInt()

    val st = con.prepareStatement("DELETE FROM USUARIO WHERE id = ?")

    st.setInt(1,id)

    st.executeUpdate()
    st.close()
    con.close()
}

fun InsertTable() {
    val con = Conexion()

    println("Añade una nueva fila")
    println("Introduce el nombre:")
    val nombre = Scanner(System.`in`).nextLine()
    println("Introduce el nickname:")
    val nickname = Scanner(System.`in`).nextLine()
    println("Introduce la contraseña:")
    val pass = Scanner(System.`in`).nextLine()
    println("Introduce el teléfono:")
    val tlf = Scanner(System.`in`).nextInt()
    println("Introduce el email:")
    val email = Scanner(System.`in`).nextLine()

    val st = con.prepareStatement("INSERT INTO USUARIO (nombre, user, password, tlf, email) VALUES (?,?,?,?,?)");

    st.setString(1,nombre)
    st.setString(2,nickname)
    st.setString(3,pass)
    st.setInt(4,tlf)
    st.setString(5,email)

    st.executeUpdate()

    println("Se ha ejecutado el insert correctamente")
    println()

    st.close()
    con.close()
}

fun SelectTable(){
    val con = Conexion()
    val st = con.createStatement()
    val rs = st.executeQuery("SELECT * FROM USUARIO")
    var count = 0
    while (rs.next()) {
        print("" + rs.getString(2) + "\t")
        print("${rs.getInt(1)}\t")
        print(rs.getString(3)+ "\t")
        print("${rs.getString(4)}\t")
        print("${rs.getInt(5)}\t")
        print(rs.getString(6)+ "\n")
        count++
    }
    print("\n\t Hay $count inserciones")
    st.close()
    con.close()
}

fun UpdateTable(){
    val con = Conexion()


    println("Actualiza una fila")
    println("Introduce el id:")
    val id = Scanner(System.`in`).nextInt()
    println("Introduce el nuevo nombre:")
    val nombre = Scanner(System.`in`).nextLine()
    println("Introduce la nueva contraseña:")
    val pass = Scanner(System.`in`).nextLine()


    val st = con.prepareStatement("UPDATE USUARIO SET nombre = ?,password = ? where id =?")

    st.setString(1,nombre)
    st.setString(2,pass)
    st.setInt(3,id)

    st.executeUpdate()
    st.close()
    con.close()
}

fun Conexion() : Connection{
    val url = "jdbc:mysql://172.17.0.2:3306/GESTION"
    val usuari = "root"
    val password = "secret"
    return DriverManager.getConnection(url, usuari, password)
}