/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Utilities;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "headquartersBean")
@ViewScoped
public class HeadquartersBean  implements Serializable {

    @EJB
    private DepartmentsModel departmentsModel;
    @EJB
    private CitiesModel citiesModel;
    @EJB
    private HeadquartersModel headquarterModel;    
    
    private Headquarters headquarters;
    private Headquarters information;
    private Cities city;
    private Departments department;
    private String latitude;
    private String longitude;
    private int idInformation;
    /**
     * Creates a new instance of HeadquartersBean
     */
    /// ///////////////////////////////////////
    public Cities getCity() {
        return city;
    }

    public int getIdInformation() {
        return idInformation;
    }

    public void setIdInformation(int idInformation) {
        this.idInformation = idInformation;
    }

    public Headquarters getHeadquarters() {
        return headquarters;
    }

    public Headquarters getInformation() {
        return information;
    }

    public void setInformation(Headquarters information) {
        this.information = information;
    }

    public void setHeadquarters(Headquarters headquarters) {
        this.headquarters = headquarters;
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
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {    
        this.longitude = longitude;
    }

    //  //////////////////////////////////////
    public HeadquartersBean() {
        this.headquarters = new Headquarters();
        this.department = new Departments();
        this.city = new Cities();
    }
    public List<Headquarters> listHeadquarterForCity(int idCity){
        return headquarterModel.listHeadquartersForCity(idCity);
    }
    public List<Headquarters> listHeadquarter(){
        return headquarterModel.listHeadquarters();
    }
    public List<Headquarters> listHeadquarterWithoutElectoralProcess(){
        List<Headquarters> headquarters = new ArrayList();
        
        for(Headquarters headquarter: headquarterModel.listHeadquarters()){
            if (!headquarter.getJrvCollection().isEmpty()){
                List jrvs;
                
                if (headquarter.getJrvCollection() instanceof List)
                    jrvs = (List)headquarter.getJrvCollection();
                else
                    jrvs = new ArrayList(headquarter.getJrvCollection());
                
                Jrv jrv = (Jrv)jrvs.get(jrvs.size() - 1);
                
                if (jrv.getElectoralProcessId().end())
                    headquarters.add(headquarter);
                
            } else
                headquarters.add(headquarter);
        }
        
        return headquarters;
    }
    
    public void deleteMap(){
        int id = Integer.parseInt(Utilities.getRequestValue("frm:idInformation"));
        Headquarters sede = this.headquarterModel.getHeadquarter(id);
        
        if (sede != null) {
            if (this.headquarterModel.deleteHeadquarter(sede.getId()))
                Utilities.addMessageFlash("exito", "La sede ha sido eliminada con exito");
            else
                Utilities.addMessageFlash("error", "La sede no se ha podido eliminar");
        } else
            Utilities.addMessageFlash("error", "No se ha podido localizar la sede");
    }
    
    public void saveMap(){
        boolean existsDepartament = false, existCity = false;
        
        this.headquarters.setName(Utilities.getRequestValue("frm:name"));
        this.city.setName(Utilities.getRequestValue("frm:city"));
        this.department.setName(Utilities.getRequestValue("frm:departament"));
        this.headquarters.setX(Utilities.getRequestValue("frm:lat"));
        this.headquarters.setY(Utilities.getRequestValue("frm:lng"));
        
        if (!department.getName().isEmpty()) {
            existsDepartament = this.departmentsModel.existDepartmentByName(this.department);

            //if (!existsDepartament)// En caso que no exista el departamento lo creamos
                //existsDepartament = this.departmentsModel.insertDepartment(this.department);

            if (existsDepartament){
                this.city.setDeparmentId(this.department);

                existCity = this.citiesModel.existCitiesByNameWithDepartment(this.city);

                if (!existCity)
                    existCity = this.citiesModel.insertCities(this.city);

                if (existCity){
                    this.headquarters.setCityId(this.city);

                    if(!this.headquarterModel.existDeadquarterWithCity(headquarters))
                        if (this.headquarterModel.insertHeadquarter(this.headquarters))
                            Utilities.addMessageFlash("exito", "La sede se ha almacenado con exito");
                        else
                            Utilities.addMessageFlash("error", "No se ha podido registrar la sede");
                    else
                        Utilities.addMessageFlash("error", "La sede ya ha sido registrada");

                } else
                    Utilities.addMessageFlash("error", "No se ha podido obtener la informacion de la ciudad");

            } else
                Utilities.addMessageFlash("error", "Debe de ser una ciudad dentro de El Salvador");
        } else
                Utilities.addMessageFlash("error", "Debe seleccionar una ubicacion en el mapa");
    }
    
    public void informationForId(int id){
        this.idInformation = id;
        this.information = this.headquarterModel.getHeadquarter(id);
    }
}
