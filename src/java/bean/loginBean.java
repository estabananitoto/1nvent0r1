package bean;

import dao.ConexionDAO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import modelo.login;

@ManagedBean
public class loginBean {

    private login lo = new login();

    public login getUs() {
        return lo;
    }

    public void setUs(login lo) {
        this.lo = lo;
    }

    public void autenticar() {
        try {
            Connection con = ConexionDAO.conectar();

            String sql = "SELECT * FROM usuarios WHERE mail = ? AND contraseña = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, lo.getMail());
            ps.setString(2, lo.getContraseña());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", rs.getString("mail"));
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("trol", rs.getInt("id_rol"));

                if (rs.getString("id_rol").equals("1")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Admin.xhtml");
                } else if (rs.getString("id_rol").equals("2")) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("bodeguero.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("mesero.xhtml");
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Mail y/o Contraseña no válidos"));
            }
        } catch (IOException | SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error accediendo a Base de Datos"));
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
        }
    }

    public void verifSesionAdmin() {
        String id_rol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("trol");

        if (id_rol == null || id_rol.equals("4")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
            }
        }
    }

    public void verifSesionBodeguero() {
        String id_rol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("trol");

        if (id_rol == null || id_rol.equals("3")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
            }
        }
}

    public void verifSesionMesero() {
        String id_rol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("trol");

        if (id_rol == null || id_rol.equals("2")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException e) {
            }
        }
    }
}