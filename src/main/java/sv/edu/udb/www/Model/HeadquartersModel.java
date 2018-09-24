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
import sv.edu.udb.www.Entities.Headquarters;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class HeadquartersModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Headquarters> listHeadquarters(){
        Query query = em.createQuery("SELECT h FROM Headquarters h");
        return query.getResultList();
    }
    public List<Headquarters> listHeadquartersForCity(int id){
        try{
            Query query = em.createQuery("SELECT h FROM Headquarters h WHERE h.cityId.id = :id");
            query.setParameter("id", id);
            return query.getResultList();
        }catch(Exception ex){
            return null;
        }
    }
    public boolean insertHeadquarter(Headquarters headquarter){
        try{
            em.persist(headquarter);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Headquarters getHeadquarter(int id){
        try{
            Headquarters enti = em.find(Headquarters.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editHeadquarter(Headquarters headquarter){
        try{
            Headquarters enti = em.find(Headquarters.class, headquarter.getId());
            if(enti != null){
                enti = headquarter;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteHeadquarter(int id){
        try{
            Headquarters enti = em.find(Headquarters.class, id);
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
