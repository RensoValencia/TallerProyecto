package com.upc.indra.dao;

import com.upc.indra.be.Parametros;
import com.upc.indra.be.Participante;
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
public class ParticipanteFacade extends AbstractFacade<Participante> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipanteFacade() {
        super(Participante.class);
    }

    public List<Participante> findByIdArea(Integer idArea) {
        
        List<Participante> listParametros = null;
        try {
            Query query = em.createNamedQuery("Participante.findByIdArea");
            query.setParameter("idArea", idArea);
            
            listParametros = query.getResultList();
            
        }catch(Exception e) {
            e.printStackTrace();
            listParametros = null;
        }
        return listParametros;
    }
}