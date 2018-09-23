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
import javax.faces.view.ViewScoped;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Model.DepartmentsModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "departmentsBean")
@ViewScoped
public class DepartmentsBean implements Serializable {

    @EJB
    private DepartmentsModel departmentModel;
    
    /**
     * Creates a new instance of DepartmentsBean
     */
    public DepartmentsBean() {
        
    }
    public List<Departments> listAllDepartments(){
        return departmentModel.getDepartments();
    }
}
