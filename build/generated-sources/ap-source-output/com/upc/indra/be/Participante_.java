package com.upc.indra.be;

import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-12T19:04:26")
@StaticMetamodel(Participante.class)
public class Participante_ { 

    public static volatile SingularAttribute<Participante, Usuario> idUsuario;
    public static volatile SingularAttribute<Participante, Integer> id;
    public static volatile SingularAttribute<Participante, Character> tipoParticipante;
    public static volatile ListAttribute<Participante, DetSolParticipante> detSolParticipanteList;

}