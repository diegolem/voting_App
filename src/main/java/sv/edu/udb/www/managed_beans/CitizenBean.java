/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
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
import static sv.edu.udb.www.Entities.Citizens_.citizenTypeId;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenTypesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "citizenBean")
@RequestScoped
public class CitizenBean implements Serializable {

    //Properties and EJB
    @EJB
    private CitizenModel citizenModel;
    @EJB
    private CitizenTypesModel citizenTypesModel;
    @EJB
    private DepartmentsModel departmentModel;
    @EJB
    private CitiesModel citiesModel;
    @EJB
    private HeadquartersModel headquarterModel;

    private Citizens citizen;

    @ManagedProperty("#{param.id}")
    private int idRequest;

    private int idDepartment;

    private int idCity;

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public Citizens getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizens citizen) {
        this.citizen = citizen;
    }

    //Process & new Instance of the Bean
    public CitizenBean() {
        this.citizen = new Citizens();
        this.citizen.setCitizenTypeId(new CitizenTypes());
    }

    public void selectDepartment(int id) {
        this.idDepartment = id;
    }

    public void selectCity(int ids) {
        this.idCity = ids;
    }

    public List<Departments> getDepar() {
        return departmentModel.getDepartments();
    }

    public List<Cities> citiesForDepartment(int idDep) {
        return citiesModel.listCitiesforDepartment(idDep);
    }

    public List<Headquarters> headquartersForCities() {
        return headquarterModel.listHeadquartersForCity(this.idCity);
    }

    public void save() throws ParseException {
        if (!this.citizenModel.existsDui(citizen)) {
            if (Validacion.esDui(citizen.getDui())) {
                if (Validacion.esNombrePersona(citizen.getName()) && Validacion.esNombrePersona(citizen.getLastname())) {
                    if (Validacion.esDireccion(citizen.getAdress())) {
                        if (Validacion.isValidDate(citizen.getBirthdate())) {
                            this.citizen.setPassword(null);
                            this.citizen.setCitizenTypeId(citizenTypesModel.getCitizenTypes("CITIZN"));
                            //insertando
                            if (!this.citizenModel.insertCitizen(citizen)) {
                                Utilities.addMessageError("Error_Insert", "No se ha podido registrar al ciudadano");
                            } else {
                                Utilities.redirect("/faces/employeeRnpn/Citizens.xhtml");
                            }
                        } else {
                            Utilities.addMessageError("Error_Fecha", "El usuario debe ser mayor de edad");
                        }
                    } else {
                        Utilities.addMessageError("Error_Direccion", "Algunos caracteres son invalidos de la direccion");
                    }
                } else {
                    Utilities.addMessageError("Error_Nombre", "Por favor ingrese un nombre o apellido correcto");
                }
            } else {
                Utilities.addMessageError("Error_DUI", "El DUI no posee un formato correcto");
            }
        } else {
            Utilities.addMessageError("Error_DUI", "El DUI ingresado ya existe");
        }
    }

    public List<Citizens> listCitizens() {
        return this.citizenModel.listCitizenNormal("CITIZN");
    }

    public void update(int id, String type) {

    }

    public boolean delete(int id) {
        return this.citizenModel.deleteCitizen(id);
    }
}
