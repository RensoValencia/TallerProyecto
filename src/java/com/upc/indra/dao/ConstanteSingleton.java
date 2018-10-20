package com.upc.indra.dao;

import com.upc.indra.be.Parametros;
import com.upc.indra.enu.EstadoCapacitacion;
import com.upc.indra.enu.EstadoPlanCapacitacion;
import com.upc.indra.enu.EstadoSolicitudCapacitacionEnum;
import com.upc.indra.enu.GrupoParametrosEnum;
import com.upc.indra.enu.TipoCapacitacion;
import com.upc.indra.enu.TipoPlanPlanificacion;
import com.upc.indra.enu.TipoRecurso;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 02-may-2018
 */
@Singleton
public class ConstanteSingleton {

    @Inject public ParametrosFacade parametrosFacade;

    @Getter @Setter public Parametros tipoCapacitacionEspecializacion;
    @Getter @Setter public Parametros tipoPlanCapacitacionInterna;
    @Getter @Setter public Parametros tipoPlanCapacitacionExterna;
    @Getter @Setter public Parametros estadoSolicitudCapacitacionPendiente;
    @Getter @Setter public Parametros estadoPlanCapacitacionEnProceso;
    @Getter @Setter public Parametros estadoPlanCapacitacionPendiente;
    @Getter @Setter public Parametros tipoRecursoMaterialEscritorio;
    @Getter @Setter public Parametros tipoRecursoMaterialRecursoInformatico;
    @Getter @Setter public Parametros estadoCapacitacionPendiente;
    @Getter @Setter public Parametros estadoSolicitudCapacitacionEnProceso;
    
    @PostConstruct
    public void init() {
        tipoCapacitacionEspecializacion = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_CAPACITACION.getValue(), 
                TipoCapacitacion.ESPECIALIZACION.getValue());
        tipoPlanCapacitacionInterna = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), 
                TipoPlanPlanificacion.INTERNO.getValue());
        tipoPlanCapacitacionExterna = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_PLAN_PLANIFICACION.getValue(), 
                TipoPlanPlanificacion.EXTERNO.getValue());
        estadoSolicitudCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), 
                EstadoSolicitudCapacitacionEnum.PENDIENTE.getValue());
        estadoPlanCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), EstadoPlanCapacitacion.PENDIENTE.getValue());
        estadoPlanCapacitacionEnProceso = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_PLAN_CAPACITACION.getValue(), 
                EstadoPlanCapacitacion.EN_PROCESO.getValue());
        tipoRecursoMaterialEscritorio = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_RECURSO.getValue(), TipoRecurso.MATERIAL_ESCRITORIO.getValue());
        tipoRecursoMaterialRecursoInformatico = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.TIPO_RECURSO.getValue(), TipoRecurso.RECURSO_INFORMATICO.getValue());
        estadoCapacitacionPendiente = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_CAPACITACION.getValue(), EstadoCapacitacion.PENDIENTE.getValue());
        estadoSolicitudCapacitacionEnProceso = parametrosFacade.findByGrupoCodigo(GrupoParametrosEnum.ESTADO_SOLICITUD_CAPACITACION.getValue(), EstadoCapacitacion.EN_PROCESO.getValue());
        
        
    }
    
    public ConstanteSingleton() {
    }
}