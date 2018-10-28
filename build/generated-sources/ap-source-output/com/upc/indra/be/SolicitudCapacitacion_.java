package com.upc.indra.be;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-28T11:20:00")
@StaticMetamodel(SolicitudCapacitacion.class)
public class SolicitudCapacitacion_ { 

    public static volatile SingularAttribute<SolicitudCapacitacion, Parametros> idEstado;
    public static volatile SingularAttribute<SolicitudCapacitacion, Area> idArea;
    public static volatile SingularAttribute<SolicitudCapacitacion, Integer> periodo;
    public static volatile SingularAttribute<SolicitudCapacitacion, Parametros> idTipoCapacitacion;
    public static volatile SingularAttribute<SolicitudCapacitacion, Date> fechaDocumento;
    public static volatile SingularAttribute<SolicitudCapacitacion, Integer> id;
    public static volatile SingularAttribute<SolicitudCapacitacion, Date> fechaAtencion;
    public static volatile ListAttribute<SolicitudCapacitacion, DetalleSolicitud> detalleSolicitudList;
    public static volatile SingularAttribute<SolicitudCapacitacion, String> observacion;

}