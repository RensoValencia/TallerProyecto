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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Entity
@Table(name = "participante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p"),
    @NamedQuery(name = "Participante.findById", query = "SELECT p FROM Participante p WHERE p.id = :id"),
    @NamedQuery(name = "Participante.findByIdArea", query = "SELECT p FROM Participante p WHERE p.idUsuario.idArea.id = :idArea"),
    @NamedQuery(name = "Participante.findByIdUsuario", query = "SELECT p FROM Participante p WHERE p.idUsuario = :idUsuario")})
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    

    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @Getter @Setter private Usuario idUsuario;

    @Basic(optional = false)
    @Column(name = "TIPO_PARTICIPANTE")
    @Getter @Setter private Character tipoParticipante;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idParticipante", fetch = FetchType.LAZY)
    private List<DetSolParticipante> detSolParticipanteList;

    public Participante() {
    }

    public Participante(Integer id) {
        this.id = id;
    }

    public Participante(Integer id, Usuario idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public List<DetSolParticipante> getDetSolParticipanteList() {
        return detSolParticipanteList;
    }

    public void setDetSolParticipanteList(List<DetSolParticipante> detSolParticipanteList) {
        this.detSolParticipanteList = detSolParticipanteList;
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
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.upc.indra.be.Participante[ id=" + id + " ]";
    }

}