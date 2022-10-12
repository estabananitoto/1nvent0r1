package bean;

import dao.medidaDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Medida;

@ManagedBean
@ViewScoped
public class  medidaBean {

    private Medida medida = new Medida();
    private ArrayList<Medida> lstmed = new ArrayList();
    private ArrayList<Medida> lstmedFiltrada;
    private medidaDAO mdao = new medidaDAO();

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public ArrayList<Medida> getLstmed() {
        return lstmed;
    }

    public void setLstcat(ArrayList<Medida> lstmed) {
        this.lstmed = lstmed;
    }

    public ArrayList<Medida> getLstmedFiltrada() {
        return lstmedFiltrada;
    }

    public void setLstcatFiltrada(ArrayList<Medida> lstmedFiltrada) {
        this.lstmedFiltrada = lstmedFiltrada;
    }

    public void listar() {
    lstmed = mdao.listar();
    System.out.println(lstmed.size()); 
    }

    public void agregar() {
        mdao.agregar(medida);
        limpiar();
    }

    public void buscar(Medida m) {
        medida = mdao.Buscar(m);
    }

    public void actualizar() {
        mdao.actualizar(medida);
    }

    public void eliminar(Medida m) {
        mdao.eliminar(m);
        limpiar();
    }

    public void limpiar(){
        medida.setId_medida(0);
        medida.setUnidadmedida("");
}
    }
