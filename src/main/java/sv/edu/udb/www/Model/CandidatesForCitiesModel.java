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
import sv.edu.udb.www.Entities.CandidatesForCities;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CandidatesForCitiesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<CandidatesForCities> listCandidatesCities(){
        Query query = em.createQuery("SELECT c FROM CandidatesForCities c");
        return query.getResultList();
    }
    
    public boolean insertCandidatesCities(CandidatesForCities candidatesCities){
        try{
            em.persist(candidatesCities);
            em.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public CandidatesForCities getCandidatesCities(int id){
        try{
            CandidatesForCities enti = em.find(CandidatesForCities.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCandidatesCities(CandidatesForCities candidatesCities){
        try{
            CandidatesForCities enti = em.find(CandidatesForCities.class, candidatesCities.getId());
            if(enti != null){
                enti = candidatesCities;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteCandidatesCities(int id){
        try{
            CandidatesForCities enti = em.find(CandidatesForCities.class, id);
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
