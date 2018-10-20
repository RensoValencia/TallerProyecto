package com.upc.indra.enu;
/**
 *
 * @author RENSO
 */
public enum EstadoCapacitacion {

    PENDIENTE("1"), PROGRAMADO("2"), EN_PROCESO("3"), FINALIZADO("4");
    
    private final String value;

    EstadoCapacitacion(String value) {
        this.value = value;
    }

    public Integer getValue() {
        return new Integer(value);
    }    
}