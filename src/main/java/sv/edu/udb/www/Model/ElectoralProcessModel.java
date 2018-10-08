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
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;
import sv.edu.udb.www.Utilities;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class ElectoralProcessModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<ElectoralProcess> listElectoralProcess(){
        Query query = em.createQuery("SELECT e FROM ElectoralProcess e");
        return query.getResultList();
    }
    public List<ElectoralProcess> listElectoralProcessByEndDate(){
        Query query = em.createNamedQuery("ElectoralProcess.findAllByEndDateNow");
        return query.getResultList();
    }
    
    public List<ElectoralProcess> listElectoralProcessByEndDateDepartamental(){
        Query query = em.createNamedQuery("ElectoralProcess.findAllByEndDateNowDepartament");
        return query.getResultList();
    }
    
    public List<ElectoralProcess> listElectoralProcessByEndDatePresidential(){
        Query query = em.createNamedQuery("ElectoralProcess.findAllByEndDateNowPresidential");
        return query.getResultList();
    }
    public ElectoralProcess getElectoralProcess(String codigo){
        try{
            Query query = em.createQuery("SELECT e FROM ElectoralProcess e where e.code = :code");
            query.setParameter("code", codigo);
            
            ElectoralProcess enti = (ElectoralProcess)query.getResultList().get(0);
            
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public List<ElectoralProcess> getPresidencialCandidates(ElectoralProcess electoralpro){
        try{
            Query query = em.createQuery("SELECT e FROM ElectoralProcess e where e.id = :id");
            
            query.setParameter("id", electoralpro.getId());
            
            if(query.getResultList() != null){
                return query.getResultList();
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public List<PoliticGroups> getPoliticGroupPresidencial(ElectoralProcess ele){
        try{
            Query query = em.createQuery("SELECT p.candidatesId.politicGroupId FROM PresidencialCandidates p WHERE p.electoralProcessId.id = :id");
            query.setParameter("id", ele.getId());
            
            if(query.getResultList() != null){
                return query.getResultList();
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public List<PoliticGroups> getPoliticGroupCities(ElectoralProcess ele){
        try{
            Query query = em.createQuery("SELECT p.candidateId.politicGroupId FROM CandidatesForCities p WHERE p.electoralProcessId.id = :id");
            query.setParameter("id", ele.getId());
            if(query.getResultList() != null){
                return query.getResultList();
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean insertElectoralProcess(ElectoralProcess electoralpro){
        try{
            em.persist(electoralpro);
            em.flush();
            return true;
        }catch(Exception e){
            Utilities.addMessageError("Error", e.getMessage()); 
            return false;
        }
    }
    public ElectoralProcess getElectoralProcess(int id){
        try{
            ElectoralProcess enti = em.find(ElectoralProcess.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editElectoralProcess(ElectoralProcess electoralpro){
        try{
            ElectoralProcess enti = em.find(ElectoralProcess.class, electoralpro.getId());
            if(enti != null){
                enti = electoralpro;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            Utilities.addMessageError("Error_login", "Error: " + e.getMessage());
            return false;
        }
    }
    public boolean deleteElectoralProcess(int id){
        try{
            ElectoralProcess enti = em.find(ElectoralProcess.class, id);
            if(enti != null){
                em.remove(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean existsCode (ElectoralProcess electoral){
        try {
            Query query = em.createQuery("SELECT count(e) FROM ElectoralProcess e where e.code = :code");
            query.setParameter("code", electoral.getCode());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean existsCodeWithId (ElectoralProcess electoral){
        try {
            Query query = em.createQuery("SELECT count(e) FROM ElectoralProcess e where e.code = :code AND e.id != :id");
            query.setParameter("code", electoral.getCode());
            query.setParameter("id", electoral.getId());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
}
