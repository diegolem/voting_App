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
    
    public boolean existDepartmentByName(Departments departament){
        try{
            Query query = em.createNamedQuery("Departments.findByName");
            query.setParameter("name", departament.getName());
            
            List<Departments> departaments = query.getResultList();
            
            if(departaments.size() > 0){
                
                Departments first = departaments.get(0);
                
                departament.setId(first.getId());
                departament.setCitiesAdminsCollection(first.getCitiesAdminsCollection());
                departament.setCitiesCollection(first.getCitiesCollection());
                
                return true;
            }
            
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
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
    public boolean existDepartment(Departments department){
        try {
            Query query = em.createQuery("SELECT count(d) FROM Departments d WHERE d.name = :name");
            query.setParameter("name", department.getName());
            return ((long)query.getSingleResult())== 1l;
        } catch(Exception error){
            return false;
        }
    }
    public boolean deleteDepartment(Departments departments){
        try{
            Departments enti = em.find(Departments.class, departments.getId());
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
