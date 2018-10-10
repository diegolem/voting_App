/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.PoliticGroupVotes;

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
            Logger.getLogger(CitizenVotesModel.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }
//    public boolean insertCitizenV(CitizenVotes citizenv){
//        try{
//            Query query = em.createNativeQuery("INSERT INTO citizen_votes VALUES(null,1,?,?,?)");
//            query.setParameter(1, citizenv.getElectoralProcessId().getId());
//            query.setParameter(2, citizenv.getCitizenId().getId());
//            query.setParameter(3, citizenv.getJrvId().getId());
//            
//            query.executeUpdate();
//            return true;
//            
//        }catch(Exception e){
//            return false;
//        }
//    }
    public JrvCitizen getJrv(int idAdm){
        try{
            JrvCitizen jrv = this.getJrvVotes(idAdm);
            if(jrv != null){
                return jrv;
            }
            return null;
        }catch(Exception ex){
            return null;
        }
    }
    public PoliticGroupVotes countVote(int pG,int jrv, int eP){
        try{
            Query query = em.createQuery("SELECT p FROM PoliticGroupVotes p WHERE p.electoralProcessId.id = :idPg AND p.jrvId.id = :idJrv AND p.electoralProcessId.id = :idEp");
            query.setParameter("idPg", pG);
            query.setParameter("idJrv", jrv);
            query.setParameter("idEp", eP); 
            return (PoliticGroupVotes) query.getResultList().get(0);
        }catch(Exception e){
            return null;
        }
    }
    
    public boolean verifyVote(Citizens citizen,int idAdm){
        try{
            JrvCitizen jrv = this.getJrvVotes(idAdm);
            Query query = em.createQuery("SELECT c FROM CitizenVotes c where c.jrvId.id = :jrvid AND c.citizenId.id = :idcitizen AND c.electoralProcessId.id = :idprocess");
            query.setParameter("jrvid", jrv.getJrvId().getId());
            query.setParameter("idcitizen", citizen.getId());
            query.setParameter("idprocess", jrv.getJrvId().getElectoralProcessId().getId());
            
            return query.getResultList().isEmpty();
        }catch(Exception e){
            return false;
        }
    }
    public JrvCitizen getJrvVotes(int idAdmin){
        try{
            Query query = em.createQuery("SELECT j FROM JrvCitizen j where j.citizenId.id = :id");
            query.setParameter("id", idAdmin);
            
            return (JrvCitizen) query.getResultList().get(0);
        }catch(Exception ex){
            return null;
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
