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
import sv.edu.udb.www.Entities.Jrv;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class JrvModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Jrv> listJrvs(){
        Query query = em.createQuery("SELECT j FROM Jrv j");
        return query.getResultList();
    }
    public boolean insertJrv(Jrv jrv){
        try{
            em.persist(jrv);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Jrv getJrv(int id){
        try{
            Jrv enti = em.find(Jrv.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editJrv(Jrv jrvcity){
        try{
            Jrv enti = em.find(Jrv.class, jrvcity.getId());
            if(enti != null){
                enti = jrvcity;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteJrv(int id){
        try{
            Jrv enti = em.find(Jrv.class, id);
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
