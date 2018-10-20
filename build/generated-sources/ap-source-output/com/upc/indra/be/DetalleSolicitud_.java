package com.upc.indra.be;

import com.upc.indra.be.Capacitacion;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.Formacion;
import com.upc.indra.be.SolicitudCapacitacion;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-12T19:04:26")
@StaticMetamodel(DetalleSolicitud.class)
public class DetalleSolicitud_ { 

    public static volatile SingularAttribute<DetalleSolicitud, Formacion> idFormacion;
    public static volatile SingularAttribute<DetalleSolicitud, Integer> numeroParticipantes;
    public static volatile SingularAttribute<DetalleSolicitud, SolicitudCapacitacion> idSolCap;
    public static volatile SingularAttribute<DetalleSolicitud, Integer> id;
    public static volatile ListAttribute<DetalleSolicitud, Capacitacion> capacitacionList;
    public static volatile ListAttribute<DetalleSolicitud, DetSolParticipante> detSolParticipanteList;

}