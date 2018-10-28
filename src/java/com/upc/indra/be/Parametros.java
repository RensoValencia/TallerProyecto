package com.upc.indra.be;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Entity
@Table(name = "parametros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametros.findAll", query = "SELECT p FROM Parametros p"),
    @NamedQuery(name = "Parametros.findById", query = "SELECT p FROM Parametros p WHERE p.id = :id"),
    @NamedQuery(name = "Parametros.findByGrupo", query = "SELECT p FROM Parametros p WHERE p.grupo = :grupo AND p.codigo <> 0"),
    @NamedQuery(name = "Parametros.findByGrupoCodigo", query = "SELECT p FROM Parametros p WHERE p.grupo = :grupo AND p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByCodigo", query = "SELECT p FROM Parametros p WHERE p.codigo = :codigo"),
    @NamedQuery(name = "Parametros.findByDescripcion", query = "SELECT p FROM Parametros p WHERE p.descripcion = :descripcion")})
public class Parametros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GRUPO")
    private int grupo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoFormacion", fetch = FetchType.LAZY)
    private List<Formacion> formacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoSala", fetch = FetchType.LAZY)
    private List<Formacion> formacionList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPlan", fetch = FetchType.LAZY)
    private List<PlanCapacitacion> planCapacitacionList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRecurso", fetch = FetchType.LAZY)
    private List<RecursoCapacitacion> recursoCapacitacionList;
    
    @Transient private boolean flgEstado;
    
    
    

    public Parametros() {
    }

    public Parametros(Integer id) {
        this.id = id;
    }

    public Parametros(Integer id, int grupo, int codigo, String descripcion) {
        this.id = id;
        this.grupo = grupo;
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Formacion> getFormacionList() {
        return formacionList;
    }

    public void setFormacionList(List<Formacion> formacionList) {
        this.formacionList = formacionList;
    }

    @XmlTransient
    public List<Formacion> getFormacionList1() {
        return formacionList1;
    }

    public void setFormacionList1(List<Formacion> formacionList1) {
        this.formacionList1 = formacionList1;
    }

    @XmlTransient
    public List<PlanCapacitacion> getPlanCapacitacionList() {
        return planCapacitacionList;
    }

    public void setPlanCapacitacionList(List<PlanCapacitacion> planCapacitacionList) {
        this.planCapacitacionList = planCapacitacionList;
    }

    @XmlTransient
    public List<RecursoCapacitacion> getRecursoCapacitacionList() {
        return recursoCapacitacionList;
    }

    public void setRecursoCapacitacionList(List<RecursoCapacitacion> recursoCapacitacionList) {
        this.recursoCapacitacionList = recursoCapacitacionList;
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
        if (!(object instanceof Parametros)) {
            return false;
        }
        Parametros other = (Parametros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Parametros[ id=" + id + " ]";
    }

    /**
     * @return the flgEstado
     */
    public boolean isFlgEstado() {
        return flgEstado;
    }

    /**
     * @param flgEstado the flgEstado to set
     */
    public void setFlgEstado(boolean flgEstado) {
        this.flgEstado = flgEstado;
    }

}