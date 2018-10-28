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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "solicitud_capacitacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SolicitudCapacitacion.findAll", query = "SELECT s FROM SolicitudCapacitacion s"),
    @NamedQuery(name = "SolicitudCapacitacion.findListSolCapByEstado", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idEstado = :estado ORDER BY s.id DESC"),
    @NamedQuery(name = "SolicitudCapacitacion.findListByArea", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idArea = :area ORDER BY s.fechaDocumento DESC"),
    @NamedQuery(name = "SolicitudCapacitacion.findById", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.id = :id"),
    @NamedQuery(name = "SolicitudCapacitacion.findByIdEstadoFechas", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idEstado = :estado AND s.fechaDocumento BETWEEN :fi AND :ff AND s.idTipoCapacitacion = :aaa"),
    @NamedQuery(name = "SolicitudCapacitacion.findByIdEstadoPeriodo", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idEstado = :estado AND s.periodo = :periodo AND s.idTipoCapacitacion = :aaa"),
    @NamedQuery(name = "SolicitudCapacitacion.findByIdTipoPlanCapacitacionAndEstado", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.idTipoCapacitacion = :idTipoPlanCapacitacion AND s.idEstado = :estado"),
    @NamedQuery(name = "SolicitudCapacitacion.findByFechaSolicitud", query = "SELECT s FROM SolicitudCapacitacion s WHERE s.fechaDocumento = :fechaDocumento"),})
public class SolicitudCapacitacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "FECHA_DOCUMENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaDocumento;
    
    @Basic(optional = false)
    @Column(name = "FECHA_ATENCION")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "OBSERVACION")
    private String observacion;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Parametros idEstado;
    
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Area idArea;
    
    @Column(name = "PERIODO")
    @Getter @Setter private Integer periodo;
    
    @JoinColumn(name = "ID_TIPO_CAPACITACION", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter private Parametros idTipoCapacitacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolCap", fetch = FetchType.LAZY)
    private List<DetalleSolicitud> detalleSolicitudList;
    
    @Transient @Getter @Setter private boolean estadoTemp;

    public SolicitudCapacitacion() {
    }

    public SolicitudCapacitacion(Integer id) {
        this.id = id;
    }

    public SolicitudCapacitacion(Integer id, Date fechaDocumento, Date fechaAtencion, String observacion) {
        this.id = id;
        this.fechaDocumento = fechaDocumento;
        this.fechaAtencion = fechaAtencion;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Date fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Parametros getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Parametros idEstado) {
        this.idEstado = idEstado;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
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
        if (!(object instanceof SolicitudCapacitacion)) {
            return false;
        }
        SolicitudCapacitacion other = (SolicitudCapacitacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.SolicitudCapacitacion[ id=" + id + " ]";
    }

}