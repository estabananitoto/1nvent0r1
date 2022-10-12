package dao;

import com.mysql.cj.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDAO {

    public static Connection conectar() {
        Connection con = null;

        try {
            Driver drv = new Driver();
            DriverManager.registerDriver(drv);
            
            String cad = "jdbc:mysql://localhost:3306/inventory_v2?user=root&useSSL=false";
            con = DriverManager.getConnection(cad);
            
        } catch (SQLException e) {
            System.out.println("Error en Conexión a Base de Datos");
        }

        return con;
    }
}

