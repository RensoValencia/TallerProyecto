package com.upc.indra.be;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Entity
@Table(name = "detalle_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleSolicitud.findAll", query = "SELECT d FROM DetalleSolicitud d"),
    @NamedQuery(name = "DetalleSolicitud.findBySolCap", query = "SELECT d FROM DetalleSolicitud d WHERE d.idSolCap = :solCap"),
    @NamedQuery(name = "DetalleSolicitud.findByEstadoTipoCapaAnioYCurso", query = "SELECT d FROM DetalleSolicitud d WHERE d.idSolCap.idEstado = :estado AND d.idFormacion.idTipoModalidad = :idTipCapa AND d.idSolCap.periodo = :periodo AND d.idFormacion.idTipoFormacion = :idTipForm"),
    @NamedQuery(name = "DetalleSolicitud.findById", query = "SELECT d FROM DetalleSolicitud d WHERE d.id = :id")})
public class DetalleSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetSol", fetch = FetchType.LAZY)
    private List<Capacitacion> capacitacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDetSolicitud", fetch = FetchType.LAZY)
    private List<DetSolParticipante> detSolParticipanteList;
    @JoinColumn(name = "ID_FORMACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Formacion idFormacion;
    @JoinColumn(name = "ID_SOL_CAP", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SolicitudCapacitacion idSolCap;
    
    @Column(name = "NUMERO_PARTICIPANTES")
    @Getter @Setter private int numeroParticipantes;
    
    @Transient @Getter @Setter private Date fechaInicio;
    @Transient @Getter @Setter private Date fechaFin;
    
    @Transient @Getter @Setter private PerfilCapacitador perfilCapacitador;
    @Transient @Getter @Setter private double honorarios;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }

    @XmlTransient
    public List<DetSolParticipante> getDetSolParticipanteList() {
        return detSolParticipanteList;
    }

    public void setDetSolParticipanteList(List<DetSolParticipante> detSolParticipanteList) {
        this.detSolParticipanteList = detSolParticipanteList;
    }

    public Formacion getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(Formacion idFormacion) {
        this.idFormacion = idFormacion;
    }

    public SolicitudCapacitacion getIdSolCap() {
        return idSolCap;
    }

    public void setIdSolCap(SolicitudCapacitacion idSolCap) {
        this.idSolCap = idSolCap;
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
        if (!(object instanceof DetalleSolicitud)) {
            return false;
        }
        DetalleSolicitud other = (DetalleSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.DetalleSolicitud[ id=" + id + " ]";
    }

}