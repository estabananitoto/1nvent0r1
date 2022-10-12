package bean;

import dao.categoriaDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Categoria;

@ManagedBean
@ViewScoped
public class categoriaBean {

    private Categoria categoria = new Categoria();
    private ArrayList<Categoria> lstcat = new ArrayList();
    private ArrayList<Categoria> lstcatFiltrada;
    private categoriaDAO cdao = new categoriaDAO();

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Categoria> getLstcat() {
        return lstcat;
    }

    public void setLstcat(ArrayList<Categoria> lstcat) {
        this.lstcat = lstcat;
    }

    public ArrayList<Categoria> getLstcatFiltrada() {
        return lstcatFiltrada;
    }

    public void setLstcatFiltrada(ArrayList<Categoria> lstcatFiltrada) {
        this.lstcatFiltrada = lstcatFiltrada;
    }

    public void listar() {
        lstcat = cdao.listar();
    }

    public void agregar() {
        cdao.agregar(categoria);
        limpiar();
    }

    public void buscar(Categoria c) {
        categoria = cdao.Buscar(c);
    }

    public void actualizar() {
        cdao.actualizar(categoria);
    }

    public void eliminar(Categoria c) {
        cdao.eliminar(c);
        limpiar();
    }

    public void limpiar(){
        categoria.setId_categoria(0);
        categoria.setNomCategoria("");
}

}
