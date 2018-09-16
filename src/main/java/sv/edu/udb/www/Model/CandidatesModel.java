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
import sv.edu.udb.www.Entities.Candidates;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CandidatesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Candidates> listCandidates(){
        Query query = em.createQuery("SELECT c FROM Candidates c");
        return query.getResultList();
    }
    public boolean insertCandidates(Candidates candidates){
        try{
            em.persist(candidates);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Candidates getCandidates(int id){
        try{
            Candidates enti = em.find(Candidates.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCandidates(Candidates candidates){
        try{
            Candidates enti = em.find(Candidates.class, candidates.getId());
            if(enti != null){
                enti = candidates;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteCandidates(int id){
        try{
            Candidates enti = em.find(Candidates.class, id);
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
