package com.upc.indra.bean;

import com.upc.indra.be.Parametros;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.bean.util.ControladorAbstracto;
import com.upc.indra.bean.util.JsfUtil;
import com.upc.indra.dao.ConstanteSingleton;
import com.upc.indra.dao.ParametrosFacade;
import com.upc.indra.dao.PlanCapacitacionFacade;
import com.upc.indra.enu.EstadoPlanCapacitacion;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoPlanPlanificacion;
import java.io.Serializable;
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
@Named(value="listadoPlanCapacitacion")
@ViewScoped
public class ListadoPlanCapacitacion implements Serializable{
    
    @Getter @Setter private Parametros tipoCapacitacionSeleccionada;
    @Getter @Setter private Parametros estadoPlanCapacitacionSeleccionada;
    @Getter @Setter private List<PlanCapacitacion> listPlanCapacitacion; 
    @Getter @Setter private Date fechaInicio; 
    @Getter @Setter private Date fechaFin; 
    @Inject private ParametrosFacade parametrosFacade;
    @Inject private PlanCapacitacionFacade planCapacitacionFacade;
    @Inject private ConstanteSingleton constanteSingleton;
    
    @Getter @Setter private Integer anioSeleccionado;

    public ListadoPlanCapacitacion() {
    }
    
    @PostConstruct
    public void init() {
        tipoCapacitacionSeleccionada = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), TipoPlanPlanificacion.INTERNO.getValue());
        estadoPlanCapacitacionSeleccionada = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), EstadoPlanCapacitacion.PENDIENTE.getValue());
        
        fechaInicio = new Date();
        fechaFin = new Date();
        listPlanCapacitacion = planCapacitacionFacade.findByTipoPeriodoAndEstado(tipoCapacitacionSeleccionada.getId(), 
                new Integer("2018"), estadoPlanCapacitacionSeleccionada.getId());
        
    }
    
    public void modificarPlanCapacitacion(PlanCapacitacion planCapacitacion) {
        
        
        
    }
    
    public void listarCapacitaciones() {
        
        
        if(tipoCapacitacionSeleccionada.getId().compareTo(constanteSingleton.getTipoPlanCapacitacionInterna().getId()) == 0) {
            
            System.out.println("anioSeleccionado: " + anioSeleccionado);
            if(null == anioSeleccionado || 0 == anioSeleccionado) {
                JsfUtil.addErrorMessage("Por favor, Ud debe seleccionar el periodo");
                return;
            }
            
            listPlanCapacitacion = planCapacitacionFacade.findByTipoPeriodoAndEstado(tipoCapacitacionSeleccionada.getId(), 
                anioSeleccionado, estadoPlanCapacitacionSeleccionada.getId());
        } else {
            listPlanCapacitacion = planCapacitacionFacade.findByTipoFechaElaboracionAndEstado(tipoCapacitacionSeleccionada.getId(), 
                fechaInicio, fechaFin, estadoPlanCapacitacionSeleccionada.getId());
        }
        
        if(listPlanCapacitacion == null || listPlanCapacitacion.size() <= 0) {
            JsfUtil.addErrorMessage("No se encontraron resultados con los filtros ingresados.");
            return;
        }
        
        ControladorAbstracto.updateComponent("frmListadoPlanCapacitacion:dtPlanesCapacitacion"); 
    }
}