/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.util.Calendar;
import java.util.Date;
import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.apache.commons.codec.digest.DigestUtils;
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
        Query query = em.createQuery("SELECT c FROM Citizens c WHERE c.citizenTypeId.id = :id AND c.state = 1");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public List<Citizens> listCitizenForJRV(int idHeadquarter, int min, int max){
        Query query = em.createNamedQuery("Citizens.findByHeadquarter");
        query.setParameter("idHeadquarter", idHeadquarter);
        query.setFirstResult(min);
        query.setMaxResults(max);
        return query.getResultList();
    }
    
    public List<Citizens> listCitizenByDepartament(int idDept){
        Query query = em.createNamedQuery("Citizens.findAllByDepartament");
        query.setParameter("id", idDept);
        return query.getResultList();
    }
    
    public List<Citizens> listCitizenForTypes(String id){
        Query query = em.createQuery("SELECT c FROM Citizens c WHERE c.citizenTypeId.id = :id AND c.state = 1");
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    public boolean insertCitizen(Citizens citizen){
        try{
            em.persist(citizen);
            em.flush();
            System.out.println("Id: " + citizen.getId());
            return true;
        }catch(Exception e){
            Logger.getLogger(CitizenModel.class.getName()).log(Level.SEVERE, null, e);
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
    public Citizens getCitizen(String dui){
        try{
            Query query = em.createQuery("SELECT c FROM Citizens c where c.dui = :dui");
            query.setParameter("dui", dui);
            
            Citizens enti = (Citizens)query.getResultList().get(0);
            
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean verificarProcesosActivos(Citizens citizen){
        try{
            Date date = Calendar.getInstance().getTime();
            Query query = em.createQuery("SELECT j FROM JrvCitizen j WHERE j.jrvId.electoralProcessId.endDate > :hoy AND j.jrvId.electoralProcessId.initDate < :antes AND j.citizenId.dui = :dui");
            query.setParameter("hoy", date,TemporalType.TIMESTAMP);
            query.setParameter("antes", date,TemporalType.TIMESTAMP);
            query.setParameter("dui", citizen.getDui());
            if(!query.getResultList().isEmpty()){
                return true;
            }
        }catch(Exception ex){
            return false;
        }
        return false;
    }
    public Citizens getLoginCitizenAdmin(String dui,String pass){
        try{
            Query query = em.createQuery("SELECT c FROM Citizens c WHERE c.dui = :dui AND c.password = :pass");
            query.setParameter("dui", dui);
            query.setParameter("pass", DigestUtils.sha256Hex(pass));
            
            Citizens enti = (Citizens) query.getResultList().get(0);
            
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
            query.setParameter("pass", DigestUtils.sha256Hex(citizen.getPassword()));
            
            Citizens origin = (Citizens)query.getResultList().get(0);
            
            citizen.setName(origin.getName());
            citizen.setLastname(origin.getLastname());
            citizen.setAdress(origin.getAdress());
            citizen.setBirthdate(origin.getBirthdate());
            citizen.setDui(origin.getDui());
            citizen.setId(origin.getId());
            citizen.setPassword(citizen.getPassword());
            citizen.setState(origin.getState());
            
            citizen.setCandidatesCollection(origin.getCandidatesCollection());
            citizen.setCitiesAdminsCollection(origin.getCitiesAdminsCollection());
            citizen.setCitizenTypeId(origin.getCitizenTypeId());
            citizen.setCitizenVotesCollection(origin.getCitizenVotesCollection());
            citizen.setHeadquarterId(origin.getHeadquarterId());
            citizen.setJrvCitizenCollection(origin.getJrvCitizenCollection());
            
        } catch(Exception error){
            System.out.println("Error conexion: " + error.getMessage());
        }
    }
    public boolean editCitizen(Citizens citizen){
        try{
            Citizens enti = em.find(Citizens.class, citizen.getId());
            if(enti != null){
                enti = citizen;
                em.merge(enti);
                em.flush();
                System.out.println("se realizo los cambios");
                return true;
            }
            System.out.println("no se realizo los cambios");
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean enableCitizen(Citizens citizen){
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
    public boolean existsDui (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c WHERE c.dui = :dui");
            query.setParameter("dui", citizen.getDui());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    public boolean isPresident(Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui AND c.citizenTypeId.id = :idtype");
            query.setParameter("dui", citizen.getDui());
            query.setParameter("idtype", citizen.getCitizenTypeId().getId());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean verifyCitizen (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui AND c.password = :pass");
            query.setParameter("dui", citizen.getDui());
            query.setParameter("pass", citizen.getPassword());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean exists (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui AND c.password = :pass");
            query.setParameter("dui", citizen.getDui());
            query.setParameter("pass", DigestUtils.sha256Hex(citizen.getPassword()));
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean existsWithDui (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui");
            query.setParameter("dui", citizen.getDui());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean existsWithOtherDui (Citizens citizen){
        try {
            Query query = em.createQuery("SELECT count(c) FROM Citizens c where c.dui = :dui AND c.id != :id");
            query.setParameter("id",  citizen.getId());
            query.setParameter("dui", citizen.getDui());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }

    public boolean deleteCitizens(Citizens citizen){
        try{
            Citizens enti = em.find(Citizens.class, citizen.getId());
            if(enti != null){
                if(enti.getCandidatesCollection().isEmpty()){
                    if(enti.getJrvCitizenCollection().isEmpty()){
                        em.remove(enti);
                        em.flush();
                        return true;
                    }
                }   
                return false;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
}
