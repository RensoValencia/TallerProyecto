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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "formacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f"),
    @NamedQuery(name = "Formacion.findById", query = "SELECT f FROM Formacion f WHERE f.id = :id"),
    @NamedQuery(name = "Formacion.findByIdArea", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea"),
    @NamedQuery(name = "Formacion.findByIdAreaAndIdTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.tipoFormacion.id = :idTipoFormacion"),
    @NamedQuery(name = "Formacion.findByIdAreaAndIdTipoCapacitacion", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.idTipoCapacitacion.id = :idTipoCapacitacion"),
    @NamedQuery(name = "Formacion.findByIdAreaIdTipoFormacionAndIdTipoCapacitacion", query = "SELECT f FROM Formacion f WHERE f.idArea.id = :idArea AND f.tipoFormacion.id = :idTipoFormacion AND f.idTipoCapacitacion.id = :idTipoCapacitacion"),
    @NamedQuery(name = "Formacion.findByNombre", query = "SELECT f FROM Formacion f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formacion.findByMaximoParticipantes", query = "SELECT f FROM Formacion f WHERE f.maximoParticipantes = :maximoParticipantes")})
public class Formacion implements Serializable {

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
    @Column(name = "MAXIMO_PARTICIPANTES")
    private int maximoParticipantes;
    
    @JoinColumn(name = "TIPO_FORMACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros tipoFormacion;
    @JoinColumn(name = "TIPO_SALA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros tipoSala;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFormacion", fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detalleSolicitudList;
    
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter private Area idArea;
    
    @Column(name = "NUMERO_HORAS")
    @Getter @Setter private int numeroHoras;
    
    @JoinColumn(name = "ID_TIPO_CAPACITACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter private Parametros idTipoCapacitacion;

    public Formacion() {
    }

    public Formacion(Integer id) {
        this.id = id;
    }

    public Formacion(Integer id, String nombre, int maximoParticipantes) {
        this.id = id;
        this.nombre = nombre;
        this.maximoParticipantes = maximoParticipantes;
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

    public int getMaximoParticipantes() {
        return maximoParticipantes;
    }

    public void setMaximoParticipantes(int maximoParticipantes) {
        this.maximoParticipantes = maximoParticipantes;
    }

    public Parametros getTipoFormacion() {
        return tipoFormacion;
    }

    public void setTipoFormacion(Parametros tipoFormacion) {
        this.tipoFormacion = tipoFormacion;
    }

    public Parametros getTipoSala() {
        return tipoSala;
    }

    public void setTipoSala(Parametros tipoSala) {
        this.tipoSala = tipoSala;
    }

    @XmlTransient
    public List<DetalleSolicitud> getDetalleSolicitudList() {
        return detalleSolicitudList;
    }

    public void setDetalleSolicitudList(List<DetalleSolicitud> detalleSolicitudList) {
        this.detalleSolicitudList = detalleSolicitudList;
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
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Formacion[ id=" + id + " ]";
    }

}