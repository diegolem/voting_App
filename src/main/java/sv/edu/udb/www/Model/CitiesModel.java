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
    public List<Cities> listCitiesforDepartment(int id){
        try{
            Query query = em.createQuery("SELECT c FROM Cities c WHERE c.deparmentId.id = :ids");
            query.setParameter("ids", id);
            return query.getResultList();
        }catch(Exception ex){
            return null;
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
    public boolean existCitiesByNameWithDepartment(Cities city){
        try{
            
            Query query = em.createNamedQuery("Cities.findByNameAndDepartament");
            query.setParameter("name", city.getName());
            query.setParameter("id", city.getDeparmentId().getId());
            
            List<Cities> cities = query.getResultList();
            
            if(cities.size() > 0){
                Cities first = cities.get(0);
                
                city.setId(first.getId());
                city.setDeparmentId(first.getDeparmentId());
                city.setHeadquartersCollection(first.getHeadquartersCollection());
                city.setCandidatesForCitiesCollection(first.getCandidatesForCitiesCollection());
                
                return true;
            }
            
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
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
    public boolean existCities(Cities city){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Cities c WHERE c.name = :name");
            query.setParameter("name", city.getName());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    public boolean deleteCities(Cities city){
        try{
            Cities enti = em.find(Cities.class, city.getId());
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
