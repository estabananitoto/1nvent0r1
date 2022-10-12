package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuarios;

public class PersonaDAO {

    Connection conn = ConexionDAO.conectar();
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Usuarios> listar() {
        ArrayList<Usuarios> lstUsuarios = new ArrayList();

        try {

            String sql = "SELECT * FROM usuarios";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuarios u = new Usuarios();
                u.setIdentificacion(rs.getInt("Identificacion"));
                u.setId_rol(rs.getInt("Id_rol"));
                u.setNombres(rs.getString("Nombre"));
                u.setTelefono(rs.getInt("Telefono"));
                u.setMail(rs.getString("Mail"));

                lstUsuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }
        return lstUsuarios;
    }

    public void agregar(Usuarios u) {

        try {
            String sql = "INSERT INTO usuarios(identificacion, id_rol, nombre, telefono, mail) VALUES(?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getIdentificacion());
            ps.setInt(2, u.getId_rol());
            ps.setString(3, u.getNombres());
            ps.setInt(4, u.getTelefono());
            ps.setString(5, u.getMail());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }

    }

    public Usuarios Buscar(Usuarios u) {
        Usuarios tmp = null;
        try {
            String sql = "SELECT * FROM usuarios WHERE identificacion = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getIdentificacion());
            rs = ps.executeQuery();

            if (rs.next()) {
                tmp = new Usuarios();
                tmp.setIdentificacion(rs.getInt("Identificacion"));
                tmp.setId_rol(rs.getInt("Id_rol"));
                tmp.setNombres(rs.getString("Nombre"));
                tmp.setTelefono(rs.getInt("Telefono"));
                tmp.setMail(rs.getString("Mail"));
            }
        } catch (SQLException e) {
            System.out.println("Error buscando registro");
        }
        return tmp;
    }

    public void actualizar(Usuarios u) {
        try {
            String sql = "UPDATE usuarios SET identificacion = ?, id_rol = ?, nombre = ?, telefono = ?, mail = ? WHERE identificacion = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getIdentificacion());
            ps.setInt(2, u.getId_rol());
            ps.setString(3, u.getNombres());
            ps.setInt(4, u.getTelefono());
            ps.setString(5, u.getMail());
            ps.setInt(6, u.getIdentificacion());


            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error actualizando registro");
        }

    }

    public void eliminar(Usuarios u) {
        try {
            String sql = "DELETE FROM usuarios WHERE identificacion = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getIdentificacion());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando registro");
        }

    }
}
