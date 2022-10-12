package bean;

import dao.PersonaDAO;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Usuarios;

@ManagedBean
@ViewScoped
public class usuariosBean {

    private Usuarios usuarios = new Usuarios();
    private ArrayList<Usuarios> lstusu = new ArrayList();
    private ArrayList<Usuarios> lstusuFiltrada;
    private PersonaDAO udao = new PersonaDAO();

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuarios> getLstusu() {
        return lstusu;
    }

    public void setLstusu(ArrayList<Usuarios> lstusu) {
        this.lstusu = lstusu;
    }

    public ArrayList<Usuarios> getLstusuFiltrada() {
        return lstusuFiltrada;
    }

    public void setLstusuFiltrada(ArrayList<Usuarios> lstusuFiltrada) {
        this.lstusuFiltrada = lstusuFiltrada;
    }

    public void listar() {
        lstusu = udao.listar();
    }

    public void agregar() {
        udao.agregar(usuarios);
        limpiar();
    }

    public void buscar(Usuarios u) {
        usuarios = udao.Buscar(u);
    }

    public void actualizar() { 
        udao.actualizar(usuarios);
    }

    public void eliminar(Usuarios u) {
        udao.eliminar(u);
        limpiar();
    }

    public void limpiar() {
        usuarios.setIdentificacion(0);
        usuarios.setId_rol(0);
        usuarios.setNombres("");
        usuarios.setMail("");
        usuarios.setTelefono(0);
    }

}
