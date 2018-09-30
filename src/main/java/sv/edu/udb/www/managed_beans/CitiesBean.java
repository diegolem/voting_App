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
import sv.edu.udb.www.Entities.Cities;
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "citiesBean")
@ViewScoped
public class CitiesBean  implements Serializable {

    @EJB 
    private CitiesModel citiesModel;
    @EJB
    private DepartmentsModel departmentModel;
    @EJB
    private HeadquartersModel headquartersModel;
    
    private Cities city;
    
    private Departments department;

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }
    
    /**
     * Creates a new instance of CitiesBean
     */
    public CitiesBean() {
        this.city = new Cities();
        this.city.setDeparmentId(new Departments());
    }
    public void onloadRequest(){
        String code = Utilities.getParam("codigo");
        this.city = this.citiesModel.getCities(Integer.parseInt(code));  
    }
    public List<Cities> listCities(){
        return this.citiesModel.listCities();
    }
    
    public List<Departments> listDepartments(){
        return this.departmentModel.getDepartments();
    }
    public List<Headquarters> listHeadquarters(Cities city){
        return this.headquartersModel.listHeadquartersForCity(city.getId());
    }
    public List<Cities> listCitiesForDepartment(int idDepartment){
        return citiesModel.listCitiesforDepartment(idDepartment);
    }
    public void save(){
        if(Validacion.esNombrePersona(this.city.getName())){
            if(!this.citiesModel.existCities(this.city)){
                this.city.setDeparmentId(this.departmentModel.getDepartment(this.city.getDeparmentId().getId()));
                if(this.citiesModel.insertCities(this.city)){
                    Utilities.AddMessage("exito", "El municipio fue ingresado!!");
                    Utilities.redirect("/faces/generalAdministration/City.xhtml");
                }else{
                    Utilities.addMessageError("Error_Insert", "El municipio no se pudo insertar");
                }            
            }else{
                Utilities.addMessageError("Error_Exists", "El municipio con ese nombre ya existe");
            }
        }else{
            Utilities.addMessageError("Error_Name", "Algunos caracteres del nombre del municipio no estan permitidos");
        }
    }
    public void update(){
        if(Validacion.esNombrePersona(this.city.getName())){
            this.city.setDeparmentId(this.departmentModel.getDepartment(this.city.getDeparmentId().getId()));
                if(this.citiesModel.editCities(this.city)){
                    Utilities.AddMessage("exito", "El municipio fue modificado!!");
                    Utilities.redirect("/faces/generalAdministration/City.xhtml");
                }else{
                    Utilities.addMessageError("Error_Insert", "El municipio no se pudo modificar");
                }            
        }else{
            Utilities.addMessageError("Error_Name", "Algunos caracteres del nombre del municipio no estan permitidos");
        }
    }
    public String redirect() {
        return "/generalAdministration/editCity.xhtml";
    }
    public void delete(Cities city){
        this.city = city;
        if(this.citiesModel.deleteCities(this.city)){
            Utilities.AddMessage("exito", "El municipio fue eliminado!!");
            Utilities.redirect("/faces/generalAdministration/City.xhtml");
        }
        Utilities.AddMessage("exito", "Ocurrio un error al eliminar!!");
        Utilities.redirect("/faces/generalAdministration/City.xhtml");
    }
}
