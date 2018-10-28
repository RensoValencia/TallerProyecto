package com.upc.indra.dao;

import com.upc.indra.be.Area;
import com.upc.indra.be.DetSolParticipante;
import com.upc.indra.be.DetalleSolicitud;
import com.upc.indra.be.Parametros;
import com.upc.indra.be.Participante;
import com.upc.indra.be.SolicitudCapacitacion;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class SolicitudCapacitacionFacade extends AbstractFacade<SolicitudCapacitacion> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudCapacitacionFacade() {
        super(SolicitudCapacitacion.class);
    }
    
    public List<SolicitudCapacitacion> findByIdTipoPlanCapacitacionAndEstado(Parametros idTipoPlanCapacitacion, Parametros estado) {
        List<SolicitudCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("SolicitudCapacitacion.findByIdTipoPlanCapacitacionAndEstado");
            query.setParameter("idTipoPlanCapacitacion", idTipoPlanCapacitacion);
            query.setParameter("estado", estado);
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void actualizarEstado(Parametros p, SolicitudCapacitacion sc) {
        
        CriteriaBuilder criteriaBuilder = this.em.getCriteriaBuilder();
        CriteriaUpdate<SolicitudCapacitacion> updateSedePaquete = null;
        Root entitySedePaquete = null;

        updateSedePaquete = criteriaBuilder.createCriteriaUpdate(SolicitudCapacitacion.class);
        entitySedePaquete = updateSedePaquete.from(SolicitudCapacitacion.class);

        updateSedePaquete.set("estado", p);

        updateSedePaquete.where(criteriaBuilder.equal(entitySedePaquete.get("id"), sc.getId()));
        this.em.createQuery(updateSedePaquete).executeUpdate();
    }
    
    public List<SolicitudCapacitacion> findListByArea(Area a) {
        List<SolicitudCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("SolicitudCapacitacion.findListByArea");
            query.setParameter("area", a);
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<SolicitudCapacitacion> findByIdEstadoFechas(Parametros estado, Date fi, Date ff,  Parametros estado1) {
        List<SolicitudCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("SolicitudCapacitacion.findByIdEstadoFechas");
            query.setParameter("estado", estado);
            query.setParameter("fi", fi);
            query.setParameter("ff", ff);
            query.setParameter("aaa", estado1);
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<SolicitudCapacitacion> findByIdEstadoPeriodo(Parametros estado, Integer periodo, Parametros estado1) {
        List<SolicitudCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("SolicitudCapacitacion.findByIdEstadoPeriodo");
            query.setParameter("estado", estado);
            query.setParameter("periodo", String.valueOf(periodo));
            query.setParameter("aaa", estado1);
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<SolicitudCapacitacion> findListSolCapByEstado(Parametros p) {
        List<SolicitudCapacitacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("SolicitudCapacitacion.findListSolCapByEstado");
            query.setParameter("estado", p);
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public void actualizar(SolicitudCapacitacion solCap) {
        em.merge(solCap);
    }
    
    public void guardarSolicitud(SolicitudCapacitacion solCap, DetalleSolicitud listDetSol) {
        em.persist(solCap); 
        em.persist(listDetSol);
    }
    
    public void guardarSolicitudCapacitacion(SolicitudCapacitacion solCap, List<DetalleSolicitud> listDetSol, List<Participante> listParticipante) {
        
        em.persist(solCap); 
        
        for(DetalleSolicitud detSol: listDetSol) {
            detSol.setIdSolCap(solCap);
            em.persist(detSol);
            for(Participante detSolPar: listParticipante) {
                DetSolParticipante dddd = new DetSolParticipante();
                dddd.setIdDetSolicitud(detSol);
                dddd.setIdParticipante(detSolPar);
                em.persist(dddd);
            }    
        }
    }

}