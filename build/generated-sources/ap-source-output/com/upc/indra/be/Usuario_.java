package com.upc.indra.be;

import com.upc.indra.be.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-28T19:42:26")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> clave;
    public static volatile SingularAttribute<Usuario, Character> estado;
    public static volatile SingularAttribute<Usuario, Empleado> idEmpleado;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacion;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile SingularAttribute<Usuario, Date> fechaCreacionHora;
    public static volatile SingularAttribute<Usuario, String> nombre;

}