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
import sv.edu.udb.www.Entities.Citizens;
/**
 *
 * @author Diego Lemus
 */
@Stateless
public class CitizenModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<Citizens> listCitizen(){
        Query query = em.createQuery("SELECT c FROM Citizens c");
        return query.getResultList();
    }
    public List<Citizens> listCitizenNormal(String id){
        Query query = em.createQuery("SELECT c FROM Citizens c WHERE c.citizenTypeId.id = :id");
        query.setParameter("id", id);
        return query.getResultList();
    }
    public boolean insertCitizen(Citizens citizen){
        try{
            em.persist(citizen);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Citizens getCitizen(int id){
        try{
            Citizens enti = em.find(Citizens.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public void pullForDui(Citizens citizen){
        try {
            Query query = em.createQuery("SELECT c FROM Citizens c where c.dui = :dui AND c.password = :pass");
            query.setParameter("dui", citizen.getDui());
            query.setParameter("pass", citizen.getPassword());
            
            Citizens origin = (Citizens)query.getResultList().get(0);
            
            citizen.setName(origin.getName());
            citizen.setLastname(origin.getLastname());
            citizen.setAdress(origin.getAdress());
            citizen.setBirthdate(origin.getBirthdate());
            citizen.setDui(origin.getDui());
            citizen.setId(origin.getId());
            citizen.setPassword(citizen.getPassword());
            
            citizen.setCandidatesCollection(origin.getCandidatesCollection());
            citizen.setCitiesAdminsCollection(origin.getCitiesAdminsCollection());
            citizen.setCitizenTypeId(origin.getCitizenTypeId());
            citizen.setCitizenVotesCollection(origin.getCitizenVotesCollection());
            citizen.setHeadquarterId(origin.getHeadquarterId());
            citizen.setJrvCitizenCollection(origin.getJrvCitizenCollection());
            
        } catch(Exception error){
            System.out.println("Error: " + error.getMessage());
        }
    }
    public boolean editCitizen(Citizens citizen){
        try{
            Citizens enti = em.find(Citizens.class, citizen.getId());
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
            Citizens enti = em.find(Citizens.class, id);
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
    public boolean existsDui (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c WHERE c.dui = :dui");
            query.setParameter("dui", citizen.getDui());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    public boolean exists (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui AND c.password = :pass");
            query.setParameter("dui", citizen.getDui());
            query.setParameter("pass", citizen.getPassword());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
}
