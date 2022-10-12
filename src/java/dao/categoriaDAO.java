package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Categoria;

public class categoriaDAO {

    Connection conn = ConexionDAO.conectar();
    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Categoria> listar() {
        ArrayList<Categoria> lstCategoria = new ArrayList();

        try {

            String sql = "SELECT * FROM categoria";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId_categoria(rs.getInt("ID_Categoria"));
                c.setNomCategoria(rs.getString("NomCategoria"));

                lstCategoria.add(c);

            }

        } catch (SQLException e) {

            System.out.println("Error leyendo Base de Datos");

        }
        return lstCategoria;

    }

    public void agregar(Categoria c) {

        try {

            String sql = "INSERT INTO categoria(nomCategoria) values(?)";

            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNomCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }

    }

    public Categoria Buscar(Categoria c) {
        Categoria tmp = null;

        try {
            String sql = "SELECT * FROM categoria WHERE nomCategoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNomCategoria());
            rs = ps.executeQuery();

            if (rs.next()) {
                tmp = new Categoria();
                tmp.setId_categoria(rs.getInt("ID_Categoria"));
                tmp.setNomCategoria(rs.getString("NomCategoria"));
            }
        } catch (SQLException e) {
            System.out.println("Error leyendo Base de Datos");
        }
        return tmp;
    }

public Categoria buscar(int categoria){
        Categoria tmp = null;
        
        try {
            String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, categoria);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                tmp = new Categoria();
                tmp.setId_categoria(rs.getInt("ID_Categoria"));
                tmp.setNomCategoria(rs.getString("NomCategoria"));
            }
        } catch (SQLException e) {
            System.out.println("Error buscando registro");
        }
        
        return tmp;
    }

    public void actualizar(Categoria c) {
        try {
            String sql = "UPDATE categoria set nomCategoria = ? WHERE id_categoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNomCategoria());
            ps.setInt(2, c.getId_categoria());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando registro");
        }

    }

    public void eliminar(Categoria c) {
        try {
            String sql = "DELETE FROM categoria WHERE nomCategoria = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNomCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando registro");
        }
    }

}
