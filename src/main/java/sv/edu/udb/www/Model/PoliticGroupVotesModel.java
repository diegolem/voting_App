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
import sv.edu.udb.www.Entities.PoliticGroupVotes;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class PoliticGroupVotesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<PoliticGroupVotes> listPoliticGroupVotes(){
        Query query = em.createQuery("SELECT p FROM PoliticGroupVotes p");
        return query.getResultList();
    }
    
    public void changeStatus(int idJrv){
        Query query = em.createQuery("UPDATE PoliticGroupVotes p SET p.status = :status WHERE p.jrvId.id = :id");
        query.setParameter("id", idJrv);
        query.setParameter("status", new Short((short)1));
        query.executeUpdate();
    }
    
    public boolean insertPoliticGroupVotes(PoliticGroupVotes politicGroupVotes){
        try{
            em.persist(politicGroupVotes);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public PoliticGroupVotes getPoliticGroupVote(int id){
        try{
            PoliticGroupVotes enti = em.find(PoliticGroupVotes.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editPoliticGroupVote(PoliticGroupVotes politicGroupVotes){
        try{
            PoliticGroupVotes enti = em.find(PoliticGroupVotes.class, politicGroupVotes.getId());
            if(enti != null){
                enti = politicGroupVotes;
                em.merge(enti);
                em.flush();
                return true;
            }else if(enti == null){
                
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deletePoliticGroupVote(int id){
        try{
            PoliticGroupVotes enti = em.find(PoliticGroupVotes.class, id);
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
