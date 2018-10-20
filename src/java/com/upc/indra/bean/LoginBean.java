package com.upc.indra.bean;

import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.UsuarioFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author RENSO
 */
@Named(value="loginBean")
@ViewScoped
public class LoginBean implements Serializable{

    @Getter @Setter private Usuario usuario;
    @Inject private UsuarioFacade usuarioFacade;
    
    public LoginBean() {
    }
    
    @PostConstruct
    public void initLoginBean() {
        usuario = new Usuario();
    }
    
    public String iniciarSession() {
    
        String redireccion = "";
        
        if(usuario.getUsuario() == null || usuario.getUsuario().equals("")) {
            JsfUtil.addErrorMessage("Por favor ingrese su usuario.");
        } else if(usuario.getClave() == null && usuario.getClave().equals("")) {
            JsfUtil.addErrorMessage("Por favor ingrese su clave.");
        } else {
            Usuario user = usuarioFacade.findByUserAndPass(usuario.getUsuario(), usuario.getClave());
        
            if(null != user) {
                ControladorAbstracto.setSessionProperty("user", user);
                if(user.getIdArea().getId().compareTo(new Integer("8")) == 0) {//RR.HH
                    redireccion = "/faces/actualizarPlanPlanificacion/ListadoPlanCapacitacion";
                } else if(user.getIdArea().getId().compareTo(new Integer("2")) == 0) {//SISTEMAS
                    redireccion = "/faces/usuario/List";
                } else {//otras areas
                    redireccion = "/actualizarPlanPlanificacion/SolicitudesCapacitacion";
                }
                    
            } else {
                JsfUtil.addErrorMessage("Sus credenciales son incorrectas.");
            }
        }
        
        return redireccion;
    }
    
    public void cerrarSesion() {
        ControladorAbstracto.getExternalContext().invalidateSession();
    }
}