package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Medida;

public class medidaDAO {

    Connection conn = ConexionDAO.conectar();
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Medida> listar() {
        ArrayList<Medida> lstmedida = new ArrayList();

        try {

            String sql = "SELECT * FROM medida";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Medida m = new Medida();
                m.setId_medida(rs.getInt("id_medida"));
                m.setUnidadmedida(rs.getString("unidad_de_medida"));

                lstmedida.add(m);

            }

        } catch (SQLException e) {

            System.out.println("Error leyendo Base de Datos");

        }
        return lstmedida;

    }

    public void agregar(Medida m) {

        try {

            String sql = "INSERT INTO medida(unidad_de_medida) values(?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getUnidadmedida());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }

    }

    public Medida Buscar(Medida m) {
        Medida tmp = null;

        try {
            String sql = "SELECT * FROM medida WHERE unidad_de_medida = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getUnidadmedida());
            rs = ps.executeQuery();

            if (rs.next()) {
                tmp = new Medida();
                tmp.setId_medida(rs.getInt("id_medida"));
                tmp.setUnidadmedida(rs.getString("unidad_de_medida"));
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }
        return tmp;
    }

public Medida buscar(int medida){
        Medida tmp = null;
        
        try {
            String sql = "SELECT * FROM medida WHERE id_medida = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, medida);
            rs = ps.executeQuery();
            
             if (rs.next()) {
                tmp = new Medida();
                tmp.setId_medida (rs.getInt("id_medida"));
                tmp.setUnidadmedida(rs.getString("unidad_de_medida"));
            }
        } catch (SQLException e) {
            System.out.println("Error buscando registro");
        }
        
        return tmp;
    }

    public void actualizar(Medida m) {
        try {
            String sql = "UPDATE medida set unidad_de_medida = ? WHERE id_medida = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getUnidadmedida());
            ps.setInt(2, m.getId_medida());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando registro");
        }

    }

    public void eliminar(Medida m) {
        try {
            String sql = "DELETE FROM medida WHERE unidad_de_medida = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, m.getUnidadmedida());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando registro");
        }
    }

}
