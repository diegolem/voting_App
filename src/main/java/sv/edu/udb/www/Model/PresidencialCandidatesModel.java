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
import sv.edu.udb.www.Entities.PresidencialCandidates;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class PresidencialCandidatesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<PresidencialCandidates> listPresidencialCandidates(){
        Query query = em.createQuery("SELECT p FROM PresidencialCandidates p");
        return query.getResultList();
    }
    public boolean insertPresidencialCandidate(PresidencialCandidates presidencialCandidates){
        try{
            em.persist(presidencialCandidates);
            em.flush();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public PresidencialCandidates getPresidencialCandidate(int id){
        try{
            PresidencialCandidates enti = em.find(PresidencialCandidates.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public boolean editPresidencialCandidate(PresidencialCandidates presidencialCandidates){
        try{
            PresidencialCandidates enti = em.find(PresidencialCandidates.class, presidencialCandidates.getId());
            if(enti != null){
                enti = presidencialCandidates;
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
    public boolean deletePresidencialCandidate(int id){
        try{
            PresidencialCandidates enti = em.find(PresidencialCandidates.class, id);
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
