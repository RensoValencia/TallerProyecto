package com.upc.indra.bean;

import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.Participante;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.Constante;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.DetSolParticipanteFacade;
import com.upc.indra.dao.DetalleSolicitudFacade;
import com.upc.indra.dao.FormacionFacade;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.ParticipanteFacade;
import com.upc.indra.dao.SolicitudCapacitacionFacade;
import com.upc.indra.enu.EstadoSolicitudCapacitacionEnum;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoCapacitacion;
import com.upc.indra.enu.TipoFormacion;
import com.upc.indra.enu.TipoPlanPlanificacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Named(value="crearSolicitudBean")
@ViewScoped
public class CrearSolicitudBean implements Serializable{

    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros tipoFormacionSeleccionada;
    @Getter @Setter private Formacion formacionSeleccionada;
    @Getter @Setter private List<Parametros> listTipoCapacitacion; 
    @Getter @Setter private List<Parametros> listTipoFormacion; 
    @Getter @Setter private List<Formacion> listFormacion; 
    @Getter @Setter private List<Formacion> listFormacionTemp; 
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitud;
    @Getter @Setter private List<DetSolParticipante> listParticipante;
    @Getter @Setter private List<Participante> listParticipanteArea;
    @Getter @Setter private List<Participante> listParticipanteTemp;
    @Getter @Setter private boolean desactivarExterno;
    
    @Inject private ParticipanteFacade participanteFacade;
    @Inject private ConstanteSingleton constanteSingleton;
    @Inject private ParametrosFacade parametrosFacade;
    @Inject private FormacionFacade formacionFacade;
    @Inject private SolicitudCapacitacionFacade solicitudCapacitacionFacade;
    @Inject private DetalleSolicitudFacade detalleSolicitudFacade;
    @Inject private DetSolParticipanteFacade detSolParticipanteFacade;
    
    Usuario usuarioLogueado;
    SolicitudCapacitacion soliCap;
    Parametros estadoPendienteSolicitudCapacitacion;
    DetalleSolicitud detalleSolicitudSeleccionada;
    DetalleSolicitud ds;
    
    public CrearSolicitudBean() {
    }
    
    @PostConstruct
    public void init() {
        soliCap = new SolicitudCapacitacion();
        listFormacionTemp = new ArrayList<>();
        listDetalleSolicitud = new ArrayList<>();
        listParticipanteTemp = new ArrayList<>();
        listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
        listTipoFormacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_FORMACION.getValue());
        estadoPendienteSolicitudCapacitacion = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.PENDIENTE.getValue());
        usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty("user");
        
        if(usuarioLogueado.getIdArea().getId().compareTo(Constante.ID_MARKETING) == 0) {
            listFormacion = formacionFacade.findByIdAreaAndIdTipoCapacitacion(usuarioLogueado.getIdArea().getId(), 
                    constanteSingleton.getTipoCapacitacionEspecializacion().getId());
            tipoCapacitacionSeleccionada = constanteSingleton.getTipoPlanCapacitacionExterna();
        } else {
            tipoCapacitacionSeleccionada = constanteSingleton.getTipoPlanCapacitacionInterna();
            listFormacion = formacionFacade.findByIdArea(usuarioLogueado.getIdArea().getId());
            for(Parametros p: listTipoCapacitacion) {
                if(p.getId().compareTo(constanteSingleton.getTipoPlanCapacitacionExterna().getId()) == 0) {
                    p.setFlgEstado(true);
                } else {
                    p.setFlgEstado(false);
                }
            }
        }
    }
    
    public void buscarCurso() {
        if(tipoCapacitacionSeleccionada.getId().compareTo(constanteSingleton.getTipoPlanCapacitacionInterna().getId()) == 0) {
            listFormacion = formacionFacade.findByIdAreaAndIdTipoFormacion(usuarioLogueado.getIdArea().getId(), 
                tipoFormacionSeleccionada.getId());
        } else {
            listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoCapacitacion(usuarioLogueado.getIdArea().getId(), 
                tipoFormacionSeleccionada.getId(), constanteSingleton.getTipoCapacitacionEspecializacion().getId());
        }
    }
    
    public void agregarCurso() {
     
        if(null == formacionSeleccionada) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar una formación.");     
            return;
        }
        
        if(null == soliCap.getId()) {
            soliCap.setIdTipoPlanCapacitacion(tipoCapacitacionSeleccionada);
            soliCap.setObservacion("Se creo la solicitud capacitacion");
            soliCap.setFechaSolicitud(new Date());
            soliCap.setIdArea(usuarioLogueado.getIdArea());
            soliCap.setEstado(new Parametros(new Integer("35")));
            
            ds = new DetalleSolicitud();
            ds.setIdFormacion(formacionSeleccionada);
            ds.setIdSolCap(soliCap);
            
            solicitudCapacitacionFacade.guardarSolicitud(soliCap, ds); 
        } else {
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        
            if(!listDetalleSolicitud.isEmpty()) {
                for(DetalleSolicitud ddss: listDetalleSolicitud) {
                    if(ddss.getIdFormacion().getId().compareTo(formacionSeleccionada.getId()) == 0) {
                        JsfUtil.addErrorMessage("Por favor, Ud. debe agregar otra formación, la que intenta agregar ya fue agregado.");
                        return;
                    }
                }
            }
            
            if(listDetalleSolicitud.size() > 3) {
                JsfUtil.addErrorMessage("Por favor, Ud. solo debe elegir hasta un maximo de tres detalle de solicitud.");
                return;
            }
            
            ds = new DetalleSolicitud();
            ds.setIdFormacion(formacionSeleccionada);
            ds.setIdSolCap(soliCap);
            detalleSolicitudFacade.guardarDetalleSolicitud(ds);
        }   
        
        JsfUtil.addSuccessMessage("Se ha grabado satisfactoriamente el detalle de la solicitud.");
        listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        ControladorAbstracto.updateComponent("frmCrearSolicitud:dtListDetalleSolicitud");
    }
    
    public void eliminarCurso(DetalleSolicitud ds) {
        
        List<DetSolParticipante> part = detSolParticipanteFacade.findByDetSol(ds);
        
        detalleSolicitudFacade.borrarDetalleSolicitud(ds, part);
        listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        ControladorAbstracto.updateComponent("frmCrearSolicitud:dtListDetalleSolicitud");
    }
    
    public void eliminarParticipante(DetSolParticipante dsp) {
        detSolParticipanteFacade.eliminarParticipante(dsp, detalleSolicitudSeleccionada);
        listParticipante.remove(dsp);
        listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        ControladorAbstracto.updateComponent("frmCrearSolicitud:dtListDetalleSolicitud");
        ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
    }
    
    public void agregarListadoParticipantes(DetalleSolicitud ds) {
    
        /*if(constanteSingleton.getTipoCapacitacionEspecializacion().getId().compareTo(
                ds.getIdFormacion().getIdTipoCapacitacion().getId()) == 0 && 
                usuarioLogueado.getIdArea().getId().compareTo(Constante.ID_MARKETING) != 0) { 
            JsfUtil.addErrorMessage("Por favor, Ud. solo puede asociar participantes a las capacitaciones que no sean especilización.");
            return;
        }*/
        
        detalleSolicitudSeleccionada = ds; 
        listParticipante = detSolParticipanteFacade.findByDetSol(detalleSolicitudSeleccionada);
        
        ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
        ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantes').show();");
    }
    
    public void agregarListadoParticipantes2() {
        
        List<Participante> eliminarTodo = new ArrayList<>();
        listParticipanteArea = participanteFacade.findByIdArea(usuarioLogueado.getIdArea().getId());
        listParticipante = detSolParticipanteFacade.findByDetSol(detalleSolicitudSeleccionada);
        for(Participante par1: listParticipanteArea) {
            for(DetSolParticipante det11: listParticipante) {
                if(par1.getId().compareTo(det11.getIdParticipante().getId()) == 0) {
                    eliminarTodo.add(par1);
                }
            }
        }
        listParticipanteArea.removeAll(eliminarTodo);
        
        ControladorAbstracto.updateComponent("frmListadoParticipanteSeleccionar:dtListadoParticipanteSeleccionar");
        ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantesSeleccionar').show();");
    }
    
    public String confirmarGrabacion() {
        
        String redireccion = "";
        
        if(null == formacionSeleccionada) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar una formación.");     
        } else if(soliCap.getId() == null) {
            JsfUtil.addErrorMessage("Por favor, Ud debe agregar como minimo un detalle de capacitación.");     
        } else {
            JsfUtil.addErrorMessage("Se ha grabado satisfactoriamente la solicitud de capacitación.");
            redireccion = "/actualizarPlanPlanificacion/SolicitudesCapacitacion";
        }
        
        return redireccion;
    }
    
    public void guardarParticipante() {    
        
        if(listParticipanteTemp.isEmpty()) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar al menos un participante.");
            return;
        }
        
        if((detalleSolicitudSeleccionada.getNumeroParticipantes() + listParticipanteTemp.size()) > detalleSolicitudSeleccionada.getIdFormacion().getMaximoParticipantes()) {
            JsfUtil.addErrorMessage("El maximo de participantes para esta formacion es: " + detalleSolicitudSeleccionada.getIdFormacion().getMaximoParticipantes() + 
                    ", pero Ud. ha agregado: " + listParticipanteTemp.size() + " por favor elimine algunos.");
            return;
        }
         
        detSolParticipanteFacade.guardar(detalleSolicitudSeleccionada, listParticipanteTemp);
        listParticipanteTemp = new ArrayList<>();
        listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        
        JsfUtil.addErrorMessage("Se agregaron satisfactoriamente a los participantes.");
        ControladorAbstracto.updateComponent("frmCrearSolicitud:dtListDetalleSolicitud"); 
        ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantes').hide();");
        ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantesSeleccionar').hide();");
    }
    
    public void guardarSolicitud() {
        
        solicitudCapacitacionFacade.guardarSolicitudCapacitacion(soliCap, listDetalleSolicitud, listParticipanteTemp);
        JsfUtil.addErrorMessage("Se grabo satisfactoriamente la solicitud de capacitacion.");
    }
}