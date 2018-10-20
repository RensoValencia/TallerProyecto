package com.upc.indra.dao;

import com.upc.indra.be.MarcaMaterial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author RENSO
 * @date 24-abr-2018
 */
@Stateless
public class MarcaMaterialFacade extends AbstractFacade<MarcaMaterial> {

    @PersistenceContext(unitName = "TallerProyecto2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcaMaterialFacade() {
        super(MarcaMaterial.class);
    }

}