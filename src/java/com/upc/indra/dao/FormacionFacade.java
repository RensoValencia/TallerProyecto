package com.upc.indra.dao;

import com.upc.indra.be.Formacion;
import com.upc.indra.be.Parametros;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class FormacionFacade extends AbstractFacade<Formacion> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormacionFacade() {
        super(Formacion.class);
    }
    
    public List<Formacion> findByIdArea(Integer idArea) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdArea");
            query.setParameter("idArea", idArea);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByIdAreaAndIdTipoFormacion(Integer idArea, Integer idTipoFormacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaAndIdTipoFormacion");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoFormacion", idTipoFormacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }

    public List<Formacion> findByIdAreaAndIdTipoCapacitacion(Integer idArea, Integer idTipoCapacitacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaAndIdTipoCapacitacion");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoCapacitacion", idTipoCapacitacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
    
    public List<Formacion> findByIdAreaIdTipoFormacionAndIdTipoCapacitacion(Integer idArea, Integer idTipoFormacion, Integer idTipoCapacitacion) {
        List<Formacion> listParametros = null;
        try {
            Query query = em.createNamedQuery("Formacion.findByIdAreaIdTipoFormacionAndIdTipoCapacitacion");
            query.setParameter("idArea", idArea);
            query.setParameter("idTipoFormacion", idTipoFormacion);
            query.setParameter("idTipoCapacitacion", idTipoCapacitacion);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
}