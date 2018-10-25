package com.upc.indra.dao;

import com.upc.indra.be.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class PerfilFacade extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PerfilFacade() {
        super(Rol.class);
    }

}