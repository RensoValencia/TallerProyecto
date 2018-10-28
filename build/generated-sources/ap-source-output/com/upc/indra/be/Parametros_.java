package com.upc.indra.be;

import com.upc.indra.be.Formacion;
import com.upc.indra.be.PlanCapacitacion;
import com.upc.indra.be.RecursoCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-28T11:20:00")
@StaticMetamodel(Parametros.class)
public class Parametros_ { 

    public static volatile SingularAttribute<Parametros, String> descripcion;
    public static volatile SingularAttribute<Parametros, Integer> codigo;
    public static volatile ListAttribute<Parametros, Formacion> formacionList;
    public static volatile ListAttribute<Parametros, RecursoCapacitacion> recursoCapacitacionList;
    public static volatile SingularAttribute<Parametros, Integer> grupo;
    public static volatile ListAttribute<Parametros, Formacion> formacionList1;
    public static volatile SingularAttribute<Parametros, Integer> id;
    public static volatile ListAttribute<Parametros, PlanCapacitacion> planCapacitacionList;

}