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
import javax.faces.view.ViewScoped;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "departmentsBean")
@ViewScoped
public class DepartmentsBean implements Serializable {

    @EJB
    private DepartmentsModel departmentModel;
    @EJB
    private CitiesModel citiesModel;
    
    private Departments department;
    
    @ManagedProperty("#{param.id}")
    private int idRequest;

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
    
    /**
     * Creates a new instance of DepartmentsBean
     */
    public DepartmentsBean() {
        this.department = new Departments();
    }
    public void onloadRequest(){
        this.department = this.departmentModel.getDepartment(this.idRequest);
    }
    public List<Departments> listAllDepartments(){
        return departmentModel.getDepartments();
    }
    public List<Cities> listCities(Departments department){
        return this.citiesModel.listCitiesforDepartment(department.getId());
    }
    public void save(){
        if(Validacion.esNombrePersona(this.department.getName())){
            if(!this.departmentModel.existDepartment(this.department)){
                if(this.departmentModel.insertDepartment(this.department)){
                    Utilities.AddMessage("exito", "El departmento fue ingresado!!");
                    Utilities.redirect("/faces/generalAdministration/Department.xhtml");
                }else{
                    Utilities.addMessageError("Error_Insert", "El departamento no se pudo insertar");
                }            
            }else{
                Utilities.addMessageError("Error_Exists", "El departmento con ese nombre ya existe");
            }
        }else{
            Utilities.addMessageError("Error_Name", "Algunos caracteres del nombre del departmento no estan permitidos");
        }
    }
    public void update(){
        if(Validacion.esNombrePersona(this.department.getName())){
                if(this.departmentModel.editDepartment(this.department)){
                    Utilities.AddMessage("exito", "El departmento fue modificado!!");
                    Utilities.redirect("/faces/generalAdministration/Department.xhtml");
                }else{
                    Utilities.addMessageError("Error_Insert", "El departamento no se pudo modificar");
                }            
        }else{
            Utilities.addMessageError("Error_Name", "Algunos caracteres del nombre del departmento no estan permitidos");
        }
    }
    public String redirect() {
        return "/generalAdministration/editDepartment.xhtml";
    }
    public void delete(Departments department){
        this.department = department;
        if(this.departmentModel.deleteDepartment(department)){
            Utilities.AddMessage("exito", "El departamento fue eliminado!!");
            Utilities.redirect("/faces/generalAdministration/Department.xhtml");
        }
        Utilities.AddMessage("exito", "Ocurrio un error al eliminar!!");
        Utilities.redirect("/faces/generalAdministration/Department.xhtml");
    }
}
