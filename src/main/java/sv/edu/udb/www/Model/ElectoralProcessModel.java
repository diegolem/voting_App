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
    public boolean insertElectoralProcess(ElectoralProcess electoralpro){
        try{
            em.persist(electoralpro);
            em.flush();
            return true;
        }catch(Exception e){
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
            return false;
        }
    }
}
