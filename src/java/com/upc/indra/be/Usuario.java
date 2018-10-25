package com.upc.indra.be;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUserAndPass", query = "SELECT u FROM Usuario u WHERE u.nombre = :usuario AND u.clave = :clave"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @JoinColumn(name = "ID_EMPLEADO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter private Empleado idEmpleado;
    
    @NotNull
    @Column(name = "NOMBRE")
    @Getter @Setter private String nombre;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLAVE")
    @Getter @Setter private String clave;
    @Basic(optional = false)
    
    @NotNull
    @Column(name = "FECHA_CREACION")
    @Temporal(TemporalType.DATE)
    @Getter @Setter private Date fechaCreacion;
    
    @NotNull
    @Column(name = "FECHA_CREACION_HORA")
    @Temporal(TemporalType.TIME)
    @Getter @Setter private Date fechaCreacionHora;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    @Getter @Setter private Character estado;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String nombre, String clave, Date fechaCreacion, Date fechaCreacionHora, Character estado) {
        this.id = id;
        this.nombre = nombre;
        this.clave = clave;
        this.fechaCreacion = fechaCreacion;
        this.fechaCreacionHora = fechaCreacionHora;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Usuario[ id=" + id + " ]";
    }

}