package com.upc.indra.be;

import com.upc.indra.be.GriRecurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-28T11:20:00")
@StaticMetamodel(GriTipoRecurso.class)
public class GriTipoRecurso_ { 

    public static volatile SingularAttribute<GriTipoRecurso, String> codigo;
    public static volatile ListAttribute<GriTipoRecurso, GriRecurso> griRecursoList;
    public static volatile SingularAttribute<GriTipoRecurso, Integer> id;
    public static volatile SingularAttribute<GriTipoRecurso, String> nombre;

}