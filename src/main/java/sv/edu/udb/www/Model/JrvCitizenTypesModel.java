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
import sv.edu.udb.www.Entities.JrvCitizenTypes;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class JrvCitizenTypesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<JrvCitizenTypes> listJrvCitizenTypes(){
        Query query = em.createQuery("SELECT j FROM JrvCitizenTypes j");
        return query.getResultList();
    }
    public boolean insertJrvCitizenTypes(JrvCitizenTypes jrvcity){
        try{
            em.persist(jrvcity);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public JrvCitizenTypes getJrvCitizenTypes(int id){
        try{
            JrvCitizenTypes enti = em.find(JrvCitizenTypes.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editJrvCitizenTypes(JrvCitizenTypes jrvcity){
        try{
            JrvCitizenTypes enti = em.find(JrvCitizenTypes.class, jrvcity.getId());
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
    public boolean deleteJrvCitizenTypes(int id){
        try{
            JrvCitizenTypes enti = em.find(JrvCitizenTypes.class, id);
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
}
