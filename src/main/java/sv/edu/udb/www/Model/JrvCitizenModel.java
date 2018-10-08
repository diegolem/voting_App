/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.JrvCitizen;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class JrvCitizenModel {

    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<JrvCitizen> listJrvCitizens(){
        Query query = em.createQuery("SELECT j FROM JrvCitizen j");
        return query.getResultList();
    }
    public boolean insertJrvCitizen(JrvCitizen jrvcity){
        try{
            em.persist(jrvcity);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public JrvCitizen getJrvCitizen(int id){
        try{
            JrvCitizen enti = em.find(JrvCitizen.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editJrvCitizen(JrvCitizen jrvcity){
        try{
            JrvCitizen enti = em.find(JrvCitizen.class, jrvcity.getId());
            if(enti != null){
                enti = jrvcity;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteJrvCitizen(int id){
        try{
            JrvCitizen enti = em.find(JrvCitizen.class, id);
            if(enti != null){
                em.remove(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteJrvCitizenByJrv(int id){
        try{
            Query query = this.em.createQuery("DELETE FROM JrvCitizen j WHERE j.jrvId.id = :id");
            query.setParameter("id", id);
            return query.executeUpdate() > 0;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
