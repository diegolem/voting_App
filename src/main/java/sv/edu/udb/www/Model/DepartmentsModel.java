/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sv.edu.udb.www.Entities.Departments;

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
    
    public List<Departments> getDepartments(){
        Query query = em.createQuery("SELECT d FROM Departments d");
        return query.getResultList();
    }
    
    public boolean insertDepartment(Departments department){
        try{
            em.persist(department);
            em.flush();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    public Departments getDepartment(int id){
        try{
            Departments enti = em.find(Departments.class, id);
            if(enti != null){
                return enti;
            }
            return null;
        }catch(Exception e){
            return null;
        }
    }
    public boolean editDepartment(Departments department){
        try{
            Departments enti = em.find(Departments.class, department.getId());
            if(enti != null){
                enti = department;
                em.merge(enti);
                em.flush();
                return true;
            }
            return false;
        }catch(Exception e){
            return false;
        }
    }
    public boolean deleteDepartment(int id){
        try{
            Departments enti = em.find(Departments.class, id);
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