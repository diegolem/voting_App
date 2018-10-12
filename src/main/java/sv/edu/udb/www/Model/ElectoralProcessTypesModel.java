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
import sv.edu.udb.www.Entities.ElectoralProcessTypes;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class ElectoralProcessTypesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<ElectoralProcessTypes> listElectoralProcessTypes(){
        Query query = em.createQuery("SELECT e FROM ElectoralProcessTypes e");
        return query.getResultList();
    }
    public boolean insertElectoralProcessTypes(ElectoralProcessTypes electoralpro){
        try{
            em.persist(electoralpro);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public ElectoralProcessTypes getElectoralProcessTypes(int id){
        try{
            ElectoralProcessTypes enti = em.find(ElectoralProcessTypes.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editElectoralProcessTypes(ElectoralProcessTypes electoralpro){
        try{
            ElectoralProcessTypes enti = em.find(ElectoralProcessTypes.class, electoralpro.getId());
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
    public boolean deleteElectoralProcessTypes(int id){
        try{
            ElectoralProcessTypes enti = em.find(ElectoralProcessTypes.class, id);
            if(enti != null){
               if(enti.getElectoralProcessCollection().isEmpty()){
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
