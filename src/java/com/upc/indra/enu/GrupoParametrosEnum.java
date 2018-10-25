package com.upc.indra.enu;
/**
 * @author RENSO
 */
public enum GrupoParametrosEnum {
    
    /*
    -- Grupo 1 (Estado de solicitud de capacitacion)
-- Grupo 2 (Tipo de formación);
-- Grupo 3 (Tipos de sala);
-- Grupo 4 (Tipos de capacitación)
-- Grupo 5 (Detalle de solicitud nivel)
-- Grupo 6 (Tipo de plan de planificacion)
-- Grupo 7 (Estado plan de planificacion)
-- Grupo 8 (Estados de capacitacion)
-- Grupo 9 (Tipo de recursos)
    */
    
    ESTADO_SOLICITUD_CAPACITACION("1"),
    TIPO_FORMACION("2"),
    TIPO_SALA("3"),
    TIPO_MODALIDAD("4"),
    DETALLE_SOLICITUD_NIVEL("5"),
    TIPO_PLAN_PLANIFICACION("6"),
    ESTADO_PLAN_CAPACITACION("7"),
    ESTADO_CAPACITACION("8"),
    TIPO_RECURSO("9");
    
    private final String value;

    GrupoParametrosEnum(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }
}
