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
import sv.edu.udb.www.Entities.CitizenTypes;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CitizenTypesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<CitizenTypes> listCitizenTypes(){
        Query query = em.createQuery("SELECT c FROM CitizenTypes c");
        return query.getResultList();
    }
    public boolean insertCitizenTypes(CitizenTypes citizentypes){
        try{
            em.persist(citizentypes);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public CitizenTypes getCitizenTypes(String id){
        try{
            CitizenTypes enti = em.find(CitizenTypes.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCitizen(CitizenTypes citizentypes){
        try{
            CitizenTypes enti = em.find(CitizenTypes.class, citizentypes.getId());
            if(enti != null){
                enti = citizentypes;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteCitizen(String id){
        try{
            CitizenTypes enti = em.find(CitizenTypes.class, id);
            if(enti != null){
                if(enti.getCitizensCollection().isEmpty()){
                    em.remove(enti);
                    em.flush();
                    return true;
                }
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
}
