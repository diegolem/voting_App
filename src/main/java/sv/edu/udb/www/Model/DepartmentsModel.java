/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.ElectoralProcess;

/**
 *
 * @author Diego Lemus
 */
@Stateless
public class DepartmentsModel {

    @EJB
    private CitiesModel citiesModel;
    @PersistenceContext(unitName = "voting_AppPU")
    private EntityManager em;

    public List<Departments> getDepartments() {
        Query query = em.createQuery("SELECT d FROM Departments d");
        return query.getResultList();
    }

    public boolean insertDepartment(Departments department) {
        try {
            em.persist(department);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Departments getDepartment(int id) {
        try {
            Departments enti = em.find(Departments.class, id);
            if (enti != null) {
                return enti;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean existDepartmentByName(Departments departament) {
        try {
            Query query = em.createNamedQuery("Departments.findByName");
            query.setParameter("name", departament.getName());

            List<Departments> departaments = query.getResultList();

            if (departaments.size() > 0) {

                Departments first = departaments.get(0);

                departament.setId(first.getId());
                departament.setCitiesAdminsCollection(first.getCitiesAdminsCollection());
                departament.setCitiesCollection(first.getCitiesCollection());

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editDepartment(Departments department) {
        try {
            Departments enti = em.find(Departments.class, department.getId());
            if (enti != null) {
                enti = department;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean existDepartment(Departments department) {
        try {
            Query query = em.createQuery("SELECT count(d) FROM Departments d WHERE d.name = :name");
            query.setParameter("name", department.getName());
            return ((long) query.getSingleResult()) == 1l;
        } catch (Exception error) {
            return false;
        }
    }
    public List<CitizenVotes> listDepartmentCount(ElectoralProcess _pe,int idD) {
        try {
            Query query = em.createQuery("SELECT c FROM CitizenVotes c WHERE c.electoralProcessId.id = :idPe AND c.citizenId.headquarterId.cityId.deparmentId.id = :idD AND c.status = 1");
            
            query.setParameter("idPe", _pe.getId());
            query.setParameter("idD", idD);
            if(query.getResultList() != null){
                return query.getResultList();
            }else{
                return null;
            }            
        } catch (Exception error) {
            Logger.getLogger(DepartmentsModel.class.getName()).log(Level.SEVERE, null, error);
            return null;
        }
    }
    public List<CitizenVotes> listCitiesForDepartmentsCount(ElectoralProcess _pe, int idC){
        try{
            Query query = em.createQuery("SELECT c FROM CitizenVotes c WHERE c.electoralProcessId.id = :idPe AND c.citizenId.headquarterId.cityId.id = :idC AND c.status = 1");
                        
            query.setParameter("idPe", _pe.getId());
            query.setParameter("idC", idC);
            if(query.getResultList() != null){
                return query.getResultList();
            }else{
                return null;
            }
        }catch(Exception ex){
            Logger.getLogger(DepartmentsModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<CitizenVotes> listHeadquartersForCitiesCount(ElectoralProcess _pe, int idH){
        try{
            Query query = em.createQuery("SELECT c FROM CitizenVotes c WHERE c.electoralProcessId.id = :idPe AND c.citizenId.headquarterId.id = :idH AND c.status = 1");
                        
            query.setParameter("idPe", _pe.getId());
            query.setParameter("idH", idH);
            if(query.getResultList() != null){
                return query.getResultList();
            }else{
                return null;
            }
        }catch(Exception ex){
            Logger.getLogger(DepartmentsModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<CitizenVotes> listJrvForHeadquartersCount(ElectoralProcess _pe, int idJ){
        try{
            Query query = em.createQuery("SELECT c FROM CitizenVotes c WHERE c.electoralProcessId.id = :idPe AND c.jrvId.id = :idJ AND c.status = 1");
                        
            query.setParameter("idPe", _pe.getId());
            query.setParameter("idJ", idJ);
            if(query.getResultList() != null){
                return query.getResultList();
            }else{
                return null;
            }
        }catch(Exception ex){
            Logger.getLogger(DepartmentsModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<CitizenVotes> totalCount(ElectoralProcess _pe){
        try{
            Query query = em.createQuery("SELECT c FROM CitizenVotes c WHERE c.electoralProcessId.id = :idPe AND c.status = 1");
                        
            query.setParameter("idPe", _pe.getId());
            if(query.getResultList() != null){
                return query.getResultList();
            }else{
                return null;
            }
        }catch(Exception ex){
            Logger.getLogger(DepartmentsModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean deleteDepartment(Departments departments) {
        try {
            Departments enti = em.find(Departments.class, departments.getId());
            if (enti != null) {
                if (enti.getCitiesCollection().isEmpty()) {
                    em.remove(enti);
                    em.flush();
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
