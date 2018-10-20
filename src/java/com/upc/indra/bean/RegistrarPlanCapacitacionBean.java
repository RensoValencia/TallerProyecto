package com.upc.indra.bean;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.GriMarca;
import com.upc.indra.be.GriRecurso;
import com.upc.indra.be.GriTipoRecurso;
import com.upc.indra.be.IndicadorArea;
import com.upc.indra.be.MarcaMaterial;
import com.upc.indra.be.MaterialesEscritorio;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.PerfilCapacitador;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.TipoMaterial;
import com.upc.indra.be.Usuario;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.CapacitacionFacade;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.DetalleSolicitudFacade;
import com.upc.indra.dao.GriMarcaFacade;
import com.upc.indra.dao.GriRecursoFacade;
import com.upc.indra.dao.GriTipoRecursoFacade;
import com.upc.indra.dao.IndicadorAreaFacade;
import com.upc.indra.dao.MarcaMaterialFacade;
import com.upc.indra.dao.MaterialesEscritorioFacade;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.PerfilCapacitadorFacade;
import com.upc.indra.dao.PlanCapacitacionFacade;
import com.upc.indra.dao.RecursoCapacitacionFacade;
import com.upc.indra.dao.SolicitudCapacitacionFacade;
import com.upc.indra.dao.TipoMaterialFacade;
import com.upc.indra.enu.GrupoParametrosEnum;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;
/**
 * @author RENSO
 */
@Named("registrarPlanCapacitacionBean")
@ViewScoped
public class RegistrarPlanCapacitacionBean implements Serializable{

    @Inject private ParametrosFacade parametrosFacade;
    @Inject private SolicitudCapacitacionFacade solicitudCapacitacionFacade;
    @Inject private DetalleSolicitudFacade detalleSolicitudFacade;
    @Inject private TipoMaterialFacade tipoMaterialFacade;
    @Inject private MarcaMaterialFacade marcaMaterialFacade;
    @Inject private MaterialesEscritorioFacade materialesEscritorioFacade;
    @Inject private PlanCapacitacionFacade planCapacitacionFacade;
    @Inject private PerfilCapacitadorFacade perfilCapacitadorFacade;
    @Inject private IndicadorAreaFacade indicadorAreaFacade;
    @Inject private GriTipoRecursoFacade griTipoRecursoFacade;
    @Inject private GriMarcaFacade griMarcaFacade;
    @Inject private GriRecursoFacade griRecursoFacade;
    @Inject private ConstanteSingleton constanteSingleton;    
    @Inject private CapacitacionFacade capacitacionFacade;    
    @Inject private RecursoCapacitacionFacade recursoCapacitacionFacade;    
    
    @Getter @Setter private List<RecursoCapacitacion> listRecursoCapacitacion; 
    @Getter @Setter private List<GriRecurso> listGriRecurso; 
    @Getter @Setter private List<IndicadorArea> listIndicadorArea; 
    @Getter @Setter private List<Parametros> listTipoCapacitacion; 
    @Getter @Setter private List<Parametros> listTipoRecurso; 
    @Getter @Setter private List<SolicitudCapacitacion> listSolicitudCapacitacion;
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitud;
    @Getter @Setter private List<DetalleSolicitud> listDetalleSolicitudTemp;
    @Getter @Setter private List<SolicitudCapacitacion> solicitudCapacitacionSeleccionadas;
    @Getter @Setter private List<DetalleSolicitud> detalleSolicitudSeleccionadas;
    @Getter @Setter private List<DetalleSolicitud> detalleSolicitudSeleccionadasUltimo;
    @Getter @Setter private List<PerfilCapacitador> listPerfilCapacitador;
    @Getter @Setter private List<TipoMaterial> listTipoMaterial;
    @Getter @Setter private List<GriTipoRecurso> listGriTipoRecurso;
    @Getter @Setter private TipoMaterial tipoMaterialSeleccionado;
    @Getter @Setter private GriTipoRecurso griTipoRecursoSeleccionado;
    @Getter @Setter private List<MarcaMaterial> listMarcaMaterial;
    @Getter @Setter private List<GriMarca> listGriMarca;
    @Getter @Setter private List<MaterialesEscritorio> listMaterialEscritorio;
    @Getter @Setter private List<MaterialesEscritorio> listMaterialEscritorioTemp;
    @Getter @Setter private List<GriRecurso> listGriRecursoTemp;
    @Getter @Setter private List<MaterialesEscritorio> materialEscritorioSeleccionados;
    @Getter @Setter private List<GriRecurso> griRecursoSeleccionados;
    @Getter @Setter private MarcaMaterial marcaMaterialSeleccionado;
    @Getter @Setter private GriMarca griMarcaSeleccionado;
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros tipoRecursoMaterialEscritorio;
    @Getter @Setter private Parametros tipoCapacitacion;
    @Getter @Setter private Parametros estadoPlanCapacitacion;
    @Getter @Setter private List<Capacitacion> listPerfiles;
    @Getter @Setter private List<Capacitacion> listPerfilesTempl;
    @Getter @Setter private List<Capacitacion> listCapacitacion;
    @Getter @Setter private Integer anioSeleccionado;
    @Getter @Setter private Date fechaEjecucion;
    @Getter @Setter private String fechaEjecucionInicio;
    @Getter @Setter private String nombreMaterial;
    @Getter @Setter private String nombreRecursoInformatico;
    @Getter @Setter private String nombreBoton;
    @Getter boolean hayEspecializacion = false;
    
    @Getter String msg = "";
    @Getter @Setter private List<Integer> lstAnios;
    private Usuario usuarioLogueado;
    private PlanCapacitacion planCapacitacion;
    
    public void RegistrarPlanCapacitacionBean() {
    }
    
    @PostConstruct
    public void init() {
        planCapacitacion = new PlanCapacitacion();
        iniciarListas();   
        iniciarObjetos();
        fechaEjecucion = Calendar.getInstance().getTime();
        SimpleDateFormat sfd = new SimpleDateFormat("dd/MM/yyyy");
        fechaEjecucionInicio = sfd.format(fechaEjecucion);
    }
    
    private void iniciarObjetos() {
        usuarioLogueado = (Usuario) ControladorAbstracto.getSessionProperty("user");
        tipoCapacitacionSeleccionada = constanteSingleton.getTipoPlanCapacitacionInterna();
        listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdTipoPlanCapacitacionAndEstado(
                constanteSingleton.getTipoPlanCapacitacionInterna(), constanteSingleton.getEstadoSolicitudCapacitacionPendiente());
        listPerfilCapacitador = perfilCapacitadorFacade.findAll();
        tipoCapacitacion = constanteSingleton.getTipoCapacitacionEspecializacion();
        estadoPlanCapacitacion = constanteSingleton.getEstadoPlanCapacitacionPendiente();
    }
    
    private void iniciarListas() {
        listDetalleSolicitudTemp = new ArrayList<>();
        listMaterialEscritorioTemp = new ArrayList<>();
        listGriRecursoTemp = new ArrayList<>();
        lstAnios = new ArrayList<>();
        lstAnios.add(new Integer("2019"));
        lstAnios.add(new Integer("2020"));
        listTipoMaterial = tipoMaterialFacade.findAll();
        listMarcaMaterial = marcaMaterialFacade.findAll();
        listGriTipoRecurso = griTipoRecursoFacade.findAll();
        listGriMarca = griMarcaFacade.findAll();
        listTipoCapacitacion = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue());
        listTipoRecurso = parametrosFacade.findByGrupo(GrupoParametrosEnum.TIPO_RECURSO.getValue());
        List<Parametros> lll = new ArrayList<>();
        for(Parametros recu: listTipoRecurso) {
            if(recu.getId().compareTo(new Integer("34")) == 0) {
                lll.add(recu);
            }
        }
        listTipoRecurso.removeAll(lll);
        tipoRecursoMaterialEscritorio = constanteSingleton.getTipoRecursoMaterialEscritorio();
    }
    
    public void agregarMaterialSolicitud(MaterialesEscritorio materialEscritorio) {

        RecursoCapacitacion rc = new RecursoCapacitacion();
        rc.setIdObjeto(materialEscritorio.getId());
        rc.setIdPlanificacion(planCapacitacion);
        rc.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialEscritorio());
        rc.setTipo(materialEscritorio.getIdTipoMat().getNombre());
        rc.setMarca(materialEscritorio.getIdMarca().getNombre()); 
        rc.setNombre("Material escritorio"); 
        rc.setTipoMaterial(materialEscritorio); 
        
        recursoCapacitacionFacade.guardarPlanAndRecursoCapa(rc);
        listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
        listMaterialEscritorio.remove(materialEscritorio);
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
    }
    
    public void agregarMaterialInformatico(GriRecurso griRecurso) {
    
        RecursoCapacitacion rc = new RecursoCapacitacion();
        rc.setIdObjeto(griRecurso.getId());
        rc.setIdPlanificacion(planCapacitacion);
        rc.setIdTipoRecurso(constanteSingleton.getTipoRecursoMaterialRecursoInformatico());
        rc.setTipo(griRecurso.getIdGriTipoRecurso().getNombre());
        rc.setMarca(griRecurso.getIdGriMarca().getNombre()); 
        rc.setNombre("Material escritorio"); 
        rc.setTipoMaterial(griRecurso); 
        
        recursoCapacitacionFacade.guardarPlanAndRecursoCapa(rc);
        
        listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
        listGriRecurso.remove(griRecurso);
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
    }
    
    public void eliminarMaterialInformatico(RecursoCapacitacion griRecurso) {
    
        recursoCapacitacionFacade.eliminar(griRecurso);
        
        listRecursoCapacitacion = recursoCapacitacionFacade.findIdPlanCapa(planCapacitacion);
        
        if(griRecurso.getTipoMaterial() instanceof MaterialesEscritorio) {
            listMaterialEscritorio.add( (MaterialesEscritorio) griRecurso.getTipoMaterial() ); 
        } else if(griRecurso.getTipoMaterial() instanceof GriRecurso) {
            listGriRecurso.add( (GriRecurso) griRecurso.getTipoMaterial() ); 
        }
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico2");
    }
    
    public void onRowSelect(SelectEvent event) {
        
        SolicitudCapacitacion soliCap = (SolicitudCapacitacion) event.getObject();
        
        FacesMessage msgq = new FacesMessage("Solicitud de capacitacion seleccinada", String.valueOf(soliCap.getId()));
        FacesContext.getCurrentInstance().addMessage(null, msgq);
        
        listDetalleSolicitud = detalleSolicitudFacade.findBySolCap(soliCap);
        
        listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);
        
        List<DetalleSolicitud> detSolTemp = new ArrayList<>();
        for(DetalleSolicitud ds: listDetalleSolicitud) {
            for(Capacitacion capa: listCapacitacion) {
                if(ds.getIdFormacion().getId().compareTo(capa.getIdDetSol().getIdFormacion().getId()) == 0) {
                    detSolTemp.add(ds);
                } 
            }
        }
        listDetalleSolicitud.removeAll(detSolTemp);
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol"); 
    }   
    
    public void siguiente1() {
        
        if(listCapacitacion == null || listCapacitacion.isEmpty()) {
            JsfUtil.addErrorMessage("Por favor, Ud debe agregar al menos un curso para la capacitación.");
            return;
        }
        
        hayEspecializacion = false;
        msg="";
        nombreBoton = "Guardar";
        for(Capacitacion ca: listCapacitacion) {
            if(tipoCapacitacion.getId().compareTo(ca.getIdDetSol().getIdFormacion().getIdTipoCapacitacion().getId()) == 0) {
                hayEspecializacion = true;
                nombreBoton = "Siguiente";
                msg="Ud. tiene cursos de especializacion en su plan.";
            }
            if(ca.getFechaInicio() == null) {
                JsfUtil.addErrorMessage("Por favor, Ud debe seleccionar la fecha de inicio del curso: " + 
                        ca.getIdDetSol().getIdFormacion().getNombre());
                return;
            }
            
            if(ca.getFechaFin() == null) {
                JsfUtil.addErrorMessage("Por favor, Ud debe seleccionar la fecha de fin del curso: " + 
                        ca.getIdDetSol().getIdFormacion().getNombre());
                return;
            }
            
            String select1 = String.valueOf(ca.getFechaInicio().getYear()).substring(1,3); 
            String select2 = String.valueOf(anioSeleccionado).substring(2,4);
            
            if(!select1.equals(select2)) {
                JsfUtil.addErrorMessage("Por favor, no se puede elegir como fecha una diferente al anio seleccionado.");
                return;
            }
            
            if(ca.getFechaInicio().after(ca.getFechaFin())) {
                JsfUtil.addErrorMessage("Por favor, Ud debe seleccionar una fecha de inicio menor a la fecha de fin.");
                return;
            }
            capacitacionFacade.actualizarTodos(listCapacitacion);
        }        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:msg");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:btnSiguiente2");
        ControladorAbstracto.executeJavascript("$('#divPrimero').hide(); $('#divSegundo').show();");
    }
    
    public void agregarDetalleSolicitud(DetalleSolicitud detalleSolicitud) {
        
        Capacitacion capa = null;
        if(planCapacitacion.getId() == null) {
            planCapacitacion.setFechaElaboracion(new Date());
            planCapacitacion.setEstado(constanteSingleton.getEstadoPlanCapacitacionEnProceso());
            planCapacitacion.setIdTipoPlan(tipoCapacitacionSeleccionada);
            planCapacitacion.setPeriodo(anioSeleccionado);
            
            capa = new Capacitacion();
            capa.setIdDetSol(detalleSolicitud);
            capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
            capa.setIdPlanCapacitacion(planCapacitacion); 
            
            planCapacitacionFacade.grabarPlanCapa(planCapacitacion, capa);
            
        } else {
            planCapacitacion.setIdTipoPlan(tipoCapacitacionSeleccionada);
            planCapacitacion.setPeriodo(anioSeleccionado);
            
            capa = new Capacitacion();
            capa.setEstado(constanteSingleton.getEstadoCapacitacionPendiente());
            capa.setIdDetSol(detalleSolicitud); 
            capa.setIdPlanCapacitacion(planCapacitacion);
            
            planCapacitacionFacade.grabarCapa(capa);
        }

        listDetalleSolicitud.remove(detalleSolicitud);
        listCapacitacion.add(capa);

        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol2");
    }
    
    public void eliminarDetalleSolicitud(Capacitacion detalleSolicitud) {
        capacitacionFacade.eliminar(detalleSolicitud); 
        listCapacitacion = capacitacionFacade.findByIdPlanCapacitacion(planCapacitacion);
        
        listCapacitacion.remove(detalleSolicitud);
        listDetalleSolicitud.add(detalleSolicitud.getIdDetSol());
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol");
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtDetSol2");
    }
    
    public void buscarRecursoInformatico() {
        
        if(null == griTipoRecursoSeleccionado) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de recurso informatico.");
            return;
        }
        
        if(null == griMarcaSeleccionado) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar la marca del recurso informatico.");
            return;
        }
        
        if(null == nombreRecursoInformatico || nombreRecursoInformatico.equals("")) {
            JsfUtil.addErrorMessage("Por favor, Ud debe escribir el nombre del recurso informatico.");
            return;
        }
        
        listGriRecurso = griRecursoFacade.findByTipoRecursoMarcaAndNombre(
                griTipoRecursoSeleccionado.getId(), griMarcaSeleccionado.getId(), nombreRecursoInformatico);
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialInformatico");
    }
    
    public void buscarRecursoMaterial() {
        
        if(null == tipoMaterialSeleccionado) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el tipo de material.");
            return;
        }
        
        if(null == marcaMaterialSeleccionado) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar la marca.");
            return;
        }
        
        if(null == nombreMaterial || nombreMaterial.equals("")) {
            JsfUtil.addErrorMessage("Por favor, Ud debe escribir el nombre del material.");
            return;
        }
        
        listMaterialEscritorio = materialesEscritorioFacade.findByTipMatAndMarEsc(
                tipoMaterialSeleccionado, marcaMaterialSeleccionado, nombreMaterial);
        
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtMaterialEscritorio");
    }
    
    public void siguiente2() {
    
        try {
        
            if(listRecursoCapacitacion.isEmpty()) {
                JsfUtil.addErrorMessage("Por favor, Ud debe eliminar al menos un tipo de recurso como minimo.");
                return;
            }
            
            for(RecursoCapacitacion ccc: listRecursoCapacitacion) {
                if(ccc.getCantidad() < 1) {
                    JsfUtil.addErrorMessage("Por favor, Ud debe ingresar una cantidad valida.");
                    return;
                }
            
                if(ccc.getValor() < 1) {
                    JsfUtil.addErrorMessage("Por favor, Ud debe ingresar un valor");
                    return;
                }
            }
            
            recursoCapacitacionFacade.actualizar(listRecursoCapacitacion);

         
        listPerfiles = new ArrayList<>();    
        if(hayEspecializacion) {
            System.out.println("tipoCapacitacion: " + tipoCapacitacion + " - " + planCapacitacion);
            listPerfiles = capacitacionFacade.findByIdEsp1(tipoCapacitacion, planCapacitacion);
            System.out.println("listPerfiles: " + listPerfiles.size());
            ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:dtPerfilCapacitador");
            ControladorAbstracto.executeJavascript("$('#divSegundo').hide(); $('#divTercero').show();");
        } else {
            JsfUtil.addSuccessMessage("Se grabo satisfactoriamente el plan de capacitacion, las capacitaciones y los materiales para la capacitación.");
            ControladorAbstracto.getExternalContext().redirect("/TallerProyecto2/faces/actualizarPlanPlanificacion/ListadoPlanCapacitacion.xhtml");
        }
        
        } catch(Exception e) {
            
        }
    }
    
    public void siguiente3() {
        
        try {
        if(listPerfiles.isEmpty()) {
            JsfUtil.addErrorMessage("Por favor, Ud. debe por lo menor agregar un perfil de capacitador");
            return;
        }
        
        for(Capacitacion detSol: listPerfiles) {
            
            if(null == detSol.getPerfilCapacitador()) {
                JsfUtil.addErrorMessage("Por favor, Ud. debe seleccionar el perfil del capacitador");
                return;
            }
            
            if(detSol.getValor() <= 0) {
                 JsfUtil.addErrorMessage("Por favor, Ud. debe escribir un honorario correcto.");
                return;
            }
        }
        
        recursoCapacitacionFacade.guardarPerfiles(planCapacitacion, listPerfiles);
            
            JsfUtil.addSuccessMessage("Se grabo satisfactoriamente el plan de capacitacion, las capaciones, los materiales y los perfiles para los cursos de especialización.");
            ControladorAbstracto.getExternalContext().redirect("/TallerProyecto2/faces/actualizarPlanPlanificacion/ListadoPlanCapacitacion.xhtml");
        } catch(Exception ex) {
            JsfUtil.addSuccessMessage("Ha Ocurrido un error inesperado contactarse con sistemas al grabar.");
            ex.printStackTrace();
        }
    }
    
    public void cargarFechasPorTipo() {
    
        listSolicitudCapacitacion = solicitudCapacitacionFacade.findByIdTipoPlanCapacitacionAndEstado(tipoCapacitacionSeleccionada, 
                constanteSingleton.getEstadoSolicitudCapacitacionPendiente());
        lstAnios = new ArrayList<>();
        if(null != tipoCapacitacionSeleccionada) {
            if(new Integer("7").compareTo(tipoCapacitacionSeleccionada.getId()) == 0) {
                lstAnios.add(new Integer("2019"));
                lstAnios.add(new Integer("2020"));
            } else {
                lstAnios.add(new Integer("2018"));
                lstAnios.add(new Integer("2019"));
            }
        }
        ControladorAbstracto.updateComponent("frmRegistrarPlanCapacitacion:txt3", "frmRegistrarPlanCapacitacion:dtSolicitudes");
    }
    
    public void mostrarVerMas() {
        listIndicadorArea = indicadorAreaFacade.findAll();
        List<IndicadorArea> eliminar = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(IndicadorArea iiiaaa: listIndicadorArea) {
            if(year-1 != iiiaaa.getPeriodo()) {
                eliminar.add(iiiaaa);
            }
        }
        listIndicadorArea.removeAll(eliminar);
        
        ControladorAbstracto.updateComponent("frmListadoIndicador:dtListadoIndicador");
        ControladorAbstracto.executeJavascript("PF('wgvDlgIndicador').show();");
    }
}