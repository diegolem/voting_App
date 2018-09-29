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
import sv.edu.udb.www.Entities.PoliticGroups;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class PoliticGroupsModel {
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;
    
    public List<PoliticGroups> listPoliticGroups(){
        Query query = em.createQuery("SELECT p FROM PoliticGroups p");
        return query.getResultList();
    }
    public boolean insertPoliticGroup(PoliticGroups politicGroups){
        try{
            em.persist(politicGroups);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public PoliticGroups getPoliticGroup(int id){
        try{
            PoliticGroups enti = em.find(PoliticGroups.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editPoliticGroup(PoliticGroups politicGroups){
        try{
            PoliticGroups enti = em.find(PoliticGroups.class, politicGroups.getId());
            if(enti != null){
                enti = politicGroups;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean existName(PoliticGroups politicGroup){
        try {
            Query query = em.createQuery("SELECT count(p) FROM PoliticGroups p WHERE p.name = :name");
            query.setParameter("name", politicGroup.getName());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    public boolean existAcronym(PoliticGroups politicGroup){
        try {
            Query query = em.createQuery("SELECT count(p) FROM PoliticGroups p WHERE p.acronym = :acronym");
            query.setParameter("acronym", politicGroup.getAcronym());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    
    public boolean deletePoliticGroup(PoliticGroups politicGroup){
        try{
            PoliticGroups enti = em.find(PoliticGroups.class, politicGroup.getId());
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
