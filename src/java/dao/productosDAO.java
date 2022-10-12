package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Productos;

public class productosDAO {

    Connection con = ConexionDAO.conectar();
    PreparedStatement ps;
    ResultSet rs;

    public List<Productos> listar() {
        List<Productos> lstProds = null;

        try {
            String sql = "SELECT * FROM productos";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            lstProds = new ArrayList();

            while (rs.next()) {
                Productos p = new Productos();
                p.setReferencia(rs.getInt("referencia"));
                p.setNombre(rs.getString("nombre"));
                p.setId_categoria(rs.getInt("id_categoria"));
                p.setId_medida(rs.getInt("id_medida"));
                p.setPrecio_compra(rs.getInt("precio_compra"));
                p.setPrecio_venta(rs.getInt("Precio_venta"));
                p.setImpuesto(rs.getInt("impuesto"));

                categoriaDAO cdao = new categoriaDAO();
                p.setCat(cdao.buscar(rs.getInt("id_Categoria")));

                medidaDAO mdao = new medidaDAO();
                p.setM(mdao.buscar(rs.getInt("id_medida")));

                lstProds.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error listando registros");
        }
        return lstProds;
    }

    public void agregar(Productos p) {
        try {
            String sql = "INSERT INTO productos (referencia, nombre,id_categoria,id_medida, precio_compra,Precio_venta,impuesto) values(?, ?, ?, ?, ?, ?, ?)";

            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getReferencia());
            ps.setString(2, p.getNombre());
            ps.setInt(3, p.getId_categoria());
            ps.setInt(4, p.getId_medida());
            ps.setInt(5, p.getPrecio_compra());
            ps.setInt(6, p.getPrecio_venta());
            ps.setInt(7, p.getImpuesto());

            ps.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public Productos buscar(Productos pro) {
        Productos tmp = null;

        try {
            String sql = "SELECT * FROM productos WHERE referencia = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getReferencia());
            rs = ps.executeQuery();

            if (rs.next()) {
                tmp = new Productos();
                tmp.setReferencia(rs.getInt("referencia"));
                tmp.setNombre(rs.getString("nombre"));
                tmp.setId_categoria(rs.getInt("id_categoria"));
                tmp.setId_medida(rs.getInt("id_medida"));
                tmp.setPrecio_compra(rs.getInt("precio_compra"));
                tmp.setPrecio_venta(rs.getInt("precio_venta"));
                tmp.setImpuesto(rs.getInt("impuesto"));

                categoriaDAO cdao = new categoriaDAO();
                tmp.setCat(cdao.buscar(rs.getInt("id_categoria")));

                medidaDAO mdao = new medidaDAO();
                tmp.setM(mdao.buscar(rs.getInt("id_medida")));
            }
        } catch (SQLException e) {
            System.out.println("Error buscando registro");
        }

        return tmp;
    }

    public void actualizar(Productos p) {
        try {
            String sql = "UPDATE productos SET nombre = ?, id_categoria = ?, id_medida = ?, precio_compra = ?, precio_venta = ?, impuesto= ? WHERE referencia = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getId_categoria());
            ps.setInt(3, p.getId_medida());
            ps.setInt(4, p.getPrecio_compra());
            ps.setInt(5, p.getPrecio_venta());
            ps.setInt(6, p.getImpuesto());
            ps.setInt(7, p.getReferencia());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error actualizando registro");
        }
    }

    public void eliminar(Productos p) {
        try {
            String sql = "DELETE FROM productos WHERE referencia = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getReferencia());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error eliminando registro");
        }
    }
}
