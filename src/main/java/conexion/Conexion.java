package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mysql://localhost:3306/BD_TIENDITA?serverTimezone=UTC";
    private static String user = "root";
    private static String pass = "mysql";
    
    public static Connection getConnection(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(url, user, pass);
        } catch(SQLException e){
            System.out.println("Error de conexion: " + e.getMessage());
        } 
        return con;
    }
}