package com.upc.indra.be;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-12T19:04:26")
@StaticMetamodel(Formacion.class)
public class Formacion_ { 

    public static volatile SingularAttribute<Formacion, Integer> maximoParticipantes;
    public static volatile SingularAttribute<Formacion, Area> idArea;
    public static volatile SingularAttribute<Formacion, Parametros> idTipoCapacitacion;
    public static volatile SingularAttribute<Formacion, Parametros> tipoFormacion;
    public static volatile SingularAttribute<Formacion, Integer> id;
    public static volatile ListAttribute<Formacion, DetalleSolicitud> detalleSolicitudList;
    public static volatile SingularAttribute<Formacion, Integer> numeroHoras;
    public static volatile SingularAttribute<Formacion, String> nombre;
    public static volatile SingularAttribute<Formacion, Parametros> tipoSala;

}