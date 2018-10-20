package com.upc.indra.be;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Entity
@Table(name = "perfil_capacitador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PerfilCapacitador.findAll", query = "SELECT p FROM PerfilCapacitador p"),
    @NamedQuery(name = "PerfilCapacitador.findById", query = "SELECT p FROM PerfilCapacitador p WHERE p.id = :id"),
    @NamedQuery(name = "PerfilCapacitador.findByNombre", query = "SELECT p FROM PerfilCapacitador p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PerfilCapacitador.findByDescripcion", query = "SELECT p FROM PerfilCapacitador p WHERE p.descripcion = :descripcion")})
public class PerfilCapacitador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    public PerfilCapacitador() {
    }

    public PerfilCapacitador(Integer id) {
        this.id = id;
    }

    public PerfilCapacitador(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof PerfilCapacitador)) {
            return false;
        }
        PerfilCapacitador other = (PerfilCapacitador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.PerfilCapacitador[ id=" + id + " ]";
    }

}