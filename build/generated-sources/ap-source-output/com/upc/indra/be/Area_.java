package com.upc.indra.be;

import com.upc.indra.be.IndicadorArea;
import com.upc.indra.be.SolicitudCapacitacion;
import com.upc.indra.be.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-12T19:04:26")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile ListAttribute<Area, SolicitudCapacitacion> solicitudCapacitacionList;
    public static volatile ListAttribute<Area, Usuario> usuarioList;
    public static volatile ListAttribute<Area, IndicadorArea> indicadorAreaList;
    public static volatile SingularAttribute<Area, Integer> id;
    public static volatile SingularAttribute<Area, String> nombre;

}