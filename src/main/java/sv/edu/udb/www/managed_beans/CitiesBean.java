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
import javax.faces.bean.RequestScoped;
import sv.edu.udb.www.Entities.Cities;
import javax.faces.view.ViewScoped;
import sv.edu.udb.www.Model.CitiesModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "citiesBean")
@RequestScoped
public class CitiesBean  implements Serializable {

    @EJB 
    private CitiesModel citiesModel;
    /**
     * Creates a new instance of CitiesBean
     */
    public CitiesBean() {
    }
    public List<Cities> listCitiesForDepartment(int idDepartment){
        return citiesModel.listCitiesforDepartment(idDepartment);
    }
    
    public List<Cities> listCities(){
        return citiesModel.listCities();
    }
}
