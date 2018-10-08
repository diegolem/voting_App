/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.Jrv;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class JrvModel {

    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;

    public List<Jrv> listJrvs() {
        Query query = em.createQuery("SELECT j FROM Jrv j");
        return query.getResultList();
    }

    public boolean insertJrv(Jrv jrv) {
        try {
            em.persist(jrv);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Jrv getJrv(int id) {
        try {
            Jrv enti = em.find(Jrv.class, id);
            if (enti != null) {
                return enti;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existByCode(Jrv jrv) {
        try {

            Query query = em.createNamedQuery("Jrv.existByCode");
            query.setParameter("code", jrv.getCode());

            long count = (long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public boolean existByAnotherCode(Jrv jrv) {
        try {

            Query query = em.createNamedQuery("Jrv.existByAnotherCode");
            query.setParameter("code", jrv.getCode());
            query.setParameter("id", jrv.getId());

            long count = (long) query.getSingleResult();

            return count > 0;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<Jrv> listJrvByProcessAndHeadquarter(int idHeadquarter, int idProcess) {
        Query query = em.createQuery("SELECT j FROM Jrv j WHERE j.headquarterId.id = :idHeadquarter and j.electoralProcessId.id = :idProcess");
        query.setParameter("idHeadquarter", idHeadquarter);
        query.setParameter("idProcess", idProcess);

        return query.getResultList();
    }

    public boolean delete(int idHeadquarter, int idProcess) {
        try {

            Query query = em.createQuery("DELETE FROM Jrv j WHERE j.headquarterId.id = :idHeadquarter and j.electoralProcessId.id = :idProcess");
            query.setParameter("idHeadquarter", idHeadquarter);
            query.setParameter("idProcess", idProcess);

            return query.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editJrv(Jrv jrvcity) {
        try {
            Jrv enti = em.find(Jrv.class, jrvcity.getId());
            if (enti != null) {
                enti = jrvcity;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteJrv(int id) {
        try {
            Jrv enti = em.find(Jrv.class, id);
            if (enti != null) {
                em.remove(enti);
                em.flush();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public java.sql.Connection conection() {
        String PERSISTENCE_UNIT_NAME = "voting_AppPU";

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        java.sql.Connection conn = (java.sql.Connection) em.unwrap(java.sql.Connection.class);
        return conn;
    }

}
