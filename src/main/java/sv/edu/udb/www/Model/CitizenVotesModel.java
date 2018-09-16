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
import sv.edu.udb.www.Entities.CitizenVotes;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CitizenVotesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<CitizenVotes> listCitizenVotes(){
        Query query = em.createQuery("SELECT c FROM CitizenVotes c");
        return query.getResultList();
    }
    public boolean insertCitizenVotes(CitizenVotes citizenvotes){
        try{
            em.persist(citizenvotes);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public CitizenVotes getCitizenVotes(int id){
        try{
            CitizenVotes enti = em.find(CitizenVotes.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCitizenVotes(CitizenVotes citizenvotes){
        try{
            CitizenVotes enti = em.find(CitizenVotes.class, citizenvotes.getId());
            if(enti != null){
                enti = citizenvotes;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteCitizenVotes(int id){
        try{
            CitizenVotes enti = em.find(CitizenVotes.class, id);
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
