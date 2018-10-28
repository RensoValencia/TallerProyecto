package com.upc.indra.bean;

import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.Participante;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.Usuario;
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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
/**
 * @author RENSO
 */
@Named(value="crearSolicitudBean")
@ViewScoped
public class CrearSolicitudBean implements Serializable{

    @Getter @Setter private List<DetalleSolicitud> listSolicitud;
    @Getter @Setter private Integer anioSeleccionado;
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros idTipoModalidad;
    @Getter @Setter private Parametros estadoSolicitudCapacitacion;
    @Getter @Setter private Parametros tipoModalidadSeleccionada;
    @Getter @Setter private Formacion formacionSeleccionada;
    @Getter @Setter private Parametros tipoFormacionSeleccionada;
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
    @Getter @Setter private SolicitudCapacitacion soliCap;
    Parametros estadoPendienteSolicitudCapacitacion;
    DetalleSolicitud detalleSolicitudSeleccionada;
    DetalleSolicitud ds;
    Parametros tipoCapacitacion;
    Calendar calendar;
    private int cantidad;
    
    public CrearSolicitudBean() {
    }
    
    @PostConstruct
    public void init() {
        cargarObjetos();
        listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
        estadoPendienteSolicitudCapacitacion = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.PENDIENTE.getValue());
        usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty("user");
        
        calendar = Calendar.getInstance();
        calendar.setTime(new Date()); 
    }
    
    private void cargarObjetos() {
        soliCap = new SolicitudCapacitacion();
        listFormacionTemp = new ArrayList<>();
        listDetalleSolicitud = new ArrayList<>();
        listParticipanteTemp = new ArrayList<>();
        listParticipante = new ArrayList<>();
    }
    
    public void buscarSolicitudCapacitacion() {
    
        try {
            if(estadoSolicitudCapacitacion == null) {
                 JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de estado");     
                return;
            }

            if(idTipoModalidad == null) {
                 JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de capacitacion");     
                return;
            }

            if(anioSeleccionado == null) {
                 JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el periodo");     
                return;
            }

            if(tipoFormacionSeleccionada == null) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de formacion");     
                return;
            }

            listSolicitud = detalleSolicitudFacade.findByEstadoTipoCapaAnioYCurso(estadoSolicitudCapacitacion, idTipoModalidad, 
                    anioSeleccionado, tipoFormacionSeleccionada);

            if(listSolicitud.isEmpty()) {
                JsfUtil.addErrorMessage("No se encontraron resultados con los filtros ingresados");     
                return;
            }
            ControladorAbstracto.updateComponent("frmBuscarSolicitudCapacitacion:dtLstSolicitud");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en buscarSolicitudCapacitacion");     
        }
    }
    
    public void buscarFormacion() {
    
        try {
            listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                usuarioLogueado.getIdEmpleado().getIdRol().getIdArea().getId(), 
                tipoFormacionSeleccionada.getId(), 
                tipoModalidadSeleccionada.getId());
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en buscarFormacion");
        }
    }
    
    public void buscarTipoFormacionYCurso() {
        
        try {
            List<Parametros> listadoFormacion = new ArrayList<>();

            if(constanteSingleton.getTipoModalidadEspecializacion().getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                listadoFormacion.add(parametrosFacade.findById(constanteSingleton.getTipoFormacionCurso().getId()));
                listTipoFormacion = listadoFormacion;
                listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                        usuarioLogueado.getIdEmpleado().getIdRol().getIdArea().getId(), 
                        constanteSingleton.getTipoFormacionCurso().getId(),
                        tipoModalidadSeleccionada.getId());

            } else if(constanteSingleton.getTipoModalidadCorrectivo().getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                listTipoFormacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_FORMACION.getValue());
                listFormacion = new ArrayList<>();
            } else if(constanteSingleton.getTipoModalidadInductivo().getId().compareTo(
                    tipoModalidadSeleccionada.getId()) == 0) {
                listadoFormacion.add(parametrosFacade.findById(constanteSingleton.getTipoFormacionTaller().getId()));
                listTipoFormacion = listadoFormacion;            
                listFormacion = formacionFacade.findByIdAreaIdTipoFormacionAndIdTipoModalidad(
                        usuarioLogueado.getIdEmpleado().getIdRol().getIdArea().getId(), 
                        constanteSingleton.getTipoFormacionTaller().getId(),
                        tipoModalidadSeleccionada.getId()); 
            }
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en buscarTipoFormacionYCurso");
        }
    }
    
    public void seleccionarDetalleSolicitud(int cant) {
    
        try {
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            cantidad = cant;
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en seleccionarDetalleSolicitud");
        }
    }
    
    public void agregarDetalleSolicitud() {
     
        try {
            if(null == tipoModalidadSeleccionada) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de modalidad.");     
                return;
            }

            if(null == tipoFormacionSeleccionada) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de formacion.");     
                return;
            }

            if(null == formacionSeleccionada) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar la formacion.");     
                return;
            }

            if(null == soliCap.getId()) {

                if(constanteSingleton.getTipoModalidadEspecializacion().getId().compareTo(tipoModalidadSeleccionada.getId()) == 0) {
                    tipoCapacitacion = constanteSingleton.getTipoPlanCapacitacionExterna();
                } else {
                    tipoCapacitacion = constanteSingleton.getTipoPlanCapacitacionInterna();
                }
                
                soliCap.setIdTipoCapacitacion(tipoCapacitacion);
                soliCap.setObservacion("Se creo la solicitud capacitacion");
                soliCap.setIdArea(usuarioLogueado.getIdEmpleado().getIdRol().getIdArea()); 
                soliCap.setIdEstado(constanteSingleton.getEstadoSolicitudCapacitacionPendiente());
                soliCap.setPeriodo(calendar.get(Calendar.YEAR));

                ds = new DetalleSolicitud();
                ds.setIdFormacion(formacionSeleccionada);
                ds.setIdSolCap(soliCap);

                solicitudCapacitacionFacade.guardarSolicitud(soliCap, ds);
                agregarListadoParticipantes(ds);

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
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en agregarDetalleSolicitud");
        }
    }
    
    public void eliminarCurso(DetalleSolicitud ds) {
        
        try {
            List<DetSolParticipante> part = detSolParticipanteFacade.findByDetSol(ds);

            detalleSolicitudFacade.borrarDetalleSolicitud(ds, part);
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en eliminarCurso");
        }
    }
    
    public void eliminarParticipante(DetSolParticipante dsp) {
        try {
            detSolParticipanteFacade.eliminarParticipante(dsp, detalleSolicitudSeleccionada);
            listParticipante.remove(dsp);
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
            ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en eliminarParticipante");
        }
    }
    
    public void agregarListadoParticipantes(DetalleSolicitud ds) {
        
        try {
            detalleSolicitudSeleccionada = ds; 
            listParticipante = detSolParticipanteFacade.findByDetSol(detalleSolicitudSeleccionada);

            ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en agregarListadoParticipantes");
        }
    }
    
    public void agregarListadoParticipantes2() {
        
        try {
            List<Participante> eliminarTodo = new ArrayList<>();
            listParticipanteArea = participanteFacade.findByIdArea(usuarioLogueado.getIdEmpleado().getIdRol().getIdArea().getId());
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
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en agregarListadoParticipantes2");
        }
    }
    
    public String confirmarGrabacion() {
        
        String redireccion = "";
        try {
            if(null == formacionSeleccionada) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar una formación.");     
            } else if(soliCap.getId() == null) {
                JsfUtil.addErrorMessage("Por favor, Ud debe agregar como minimo un detalle de capacitación.");     
            } else {
                JsfUtil.addErrorMessage("Se ha grabado satisfactoriamente la solicitud de capacitación.");
                redireccion = "/actualizarPlanPlanificacion/frmListadoPlanCapacitacion";
            }
        
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en confirmarGrabacion");
        }
        
        return redireccion;
    }
    
    public void guardarParticipante() {    
        
        try {
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
            agregarListadoParticipantes(detalleSolicitudSeleccionada);
            listParticipanteTemp = new ArrayList<>();
            listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);

            JsfUtil.addErrorMessage("Se agregaron satisfactoriamente a los participantes.");
            ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud"); 
            ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante"); 
            ControladorAbstracto.executeJavascript("PF('wgvDlgSeleccionarParticipantesSeleccionar').hide();");
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addSuccessMessage("Hubo un error inesperado en guardarParticipante");
        }
    }
    
    public void nuevaSolicitudCapacitacion() {
        cargarObjetos();
        ControladorAbstracto.updateComponent("frmCrearSolicitudCapacitacion:dtListDetalleSolicitud");
        ControladorAbstracto.updateComponent("frmListadoParticipante:dtListadoParticipante");
        
    }
    
    public void actualizarSolicitud() {
        try {
            soliCap.setFechaDocumento(new Date());
            soliCap.setIdEstado(constanteSingleton.getEstadoSolicitudCapacitacionEnviado());
            solicitudCapacitacionFacade.actualizar(soliCap);
            
            buscarSolicitudCapacitacion();
            
            ControladorAbstracto.executeJavascript("PF('wgvDlgCrearSolicitudCapacitacion').hide();");
            JsfUtil.addSuccessMessage("Se envio correctamente la solicitud de capacitacion");
            
            ControladorAbstracto.updateComponent("frmBuscarSolicitudCapacitacion:dtLstSolicitud:" + cantidad + ":btnEditar");
            
        } catch(Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage("Hubo un error inesperado en actualizarSolicitud");
        }
    }
    
    public void guardarSolicitud() {
        solicitudCapacitacionFacade.guardarSolicitudCapacitacion(soliCap, listDetalleSolicitud, listParticipanteTemp);
        JsfUtil.addErrorMessage("Se grabo satisfactoriamente la solicitud de capacitacion.");
    }
}