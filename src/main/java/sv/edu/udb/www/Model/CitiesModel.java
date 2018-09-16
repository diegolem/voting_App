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
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.Citizen;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CitiesModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Cities> listCities(){
        Query query = em.createQuery("SELECT c FROM Cities c");
        return query.getResultList();
    }
    public boolean insertCities(Cities city){
        try{
            em.persist(city);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Cities getCities(int id){
        try{
            Cities enti = em.find(Cities.class, id);
            
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editCities(Cities city){
        try{
            Cities enti = em.find(Cities.class, city.getId());
            if(enti != null){
                enti = city;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteCities(int id){
        try{
            Cities enti = em.find(Cities.class, id);
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
