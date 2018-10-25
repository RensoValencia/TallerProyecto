package com.upc.indra.be;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.RecursoCapacitacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-21T18:48:15")
@StaticMetamodel(PlanCapacitacion.class)
public class PlanCapacitacion_ { 

    public static volatile SingularAttribute<PlanCapacitacion, Parametros> estado;
    public static volatile SingularAttribute<PlanCapacitacion, Parametros> idTipoPlan;
    public static volatile SingularAttribute<PlanCapacitacion, Integer> periodo;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaAprobacion;
    public static volatile ListAttribute<PlanCapacitacion, RecursoCapacitacion> recursoCapacitacionList;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaEjecucion;
    public static volatile SingularAttribute<PlanCapacitacion, Integer> id;
    public static volatile ListAttribute<PlanCapacitacion, Capacitacion> capacitacionList;
    public static volatile SingularAttribute<PlanCapacitacion, Date> fechaElaboracion;

}