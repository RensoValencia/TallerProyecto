package com.upc.indra.dao;

import com.upc.indra.be.GriTipoRecurso;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author RENSO
 * @date 04-may-2018
 */
@Stateless
public class GriTipoRecursoFacade extends AbstractFacade<GriTipoRecurso> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GriTipoRecursoFacade() {
        super(GriTipoRecurso.class);
    }

}