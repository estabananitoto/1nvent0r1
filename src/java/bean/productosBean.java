package bean;

import dao.productosDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import modelo.Medida;
import modelo.Productos;

@ManagedBean
@ViewScoped
public class productosBean {

    private Productos producto = new Productos();
    private List<Productos> lstPro;
    private List<Productos> lstProFiltrada;
    private productosDAO prodao = new productosDAO();

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<Productos> getLstPro() {
        return lstPro;
    }

    public void setLstPro(List<Productos> lstPro) {
        this.lstPro = lstPro;
    }

    public List<Productos> getLstProFiltrada() {
        return lstProFiltrada;
    }

    public void setLstProFiltrada(List<Productos> lstProFiltrada) {
        this.lstProFiltrada = lstProFiltrada;
    }

    public productosDAO getProdao() {
        return prodao;
    }

    public void setProdao(productosDAO prodao) {
        this.prodao = prodao;
    }

    public void listar() {
        lstPro = prodao.listar();
        System.out.println(lstPro.size());
    }

    public void agregar() {
        prodao.agregar(producto);
        limpiar();
    }

    public void buscar(Productos p) {
        producto = prodao.buscar(p);
    }

    public void actualizar() {
        prodao.actualizar(producto);
    }

    public void eliminar(Productos p) {
        prodao.eliminar(p);
        limpiar();
    }

    public void limpiar() {
        producto.setReferencia(0);
        producto.setNombre("");
        producto.setId_categoria(0);
        producto.setId_medida(0);
        producto.setPrecio_compra(0);
        producto.setPrecio_venta(0);
        producto.setImpuesto(0);
    }
}
