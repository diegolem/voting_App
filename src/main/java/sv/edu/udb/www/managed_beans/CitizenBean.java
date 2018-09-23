/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitizenTypes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenTypesModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "citizenBean")
@ViewScoped
public class CitizenBean implements Serializable {

    //Properties and EJB
    @EJB
    private CitizenModel citizenModel;
    @EJB
    private CitizenTypesModel citizenTypesModel;

    private Citizens citizen;
    
    private Departments department;
    
    private Cities city;

    @ManagedProperty("#{param.id}")
    private int idRequest;

    private int idType;

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public Citizens getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizens citizen) {
        this.citizen = citizen;
    } 

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    //Process & new Instance of the Bean
    public CitizenBean() {
        this.citizen = new Citizens();
        this.citizen.setCitizenTypeId(new CitizenTypes());
        this.department = new Departments();
        this.city = new Cities();
    }
    
    public void save(){
        
    }
    public List<Citizens> list(){
        
        return null;
    }
    public void update(int id,String type){
    
    }
    
    public void delete(Citizens citizen){
        
    }
}
