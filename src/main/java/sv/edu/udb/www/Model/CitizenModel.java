/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.Citizen;
/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CitizenModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Citizen> listCitizen(){
        Query query = em.createQuery("SELECT c FROM Citizen c");
        return query.getResultList();
    }
    public boolean insertCitizen(Citizen citizen){
        try{
            em.persist(citizen);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Citizen getCitizen(int id){
        try{
            Citizen enti = em.find(Citizen.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCitizen(Citizen citizen){
        try{
            Citizen enti = em.find(Citizen.class, citizen.getId());
            if(enti != null){
                enti = citizen;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteCitizen(int id){
        try{
            Citizen enti = em.find(Citizen.class, id);
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
    public boolean exists (Citizen citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizen c where c.dui = :dui");
            query.setParameter("dui", citizen.getDui());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
}
