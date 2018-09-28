/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitizenTypes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenTypesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
    @EJB
    private DepartmentsModel departmentModel;
    @EJB
    private CitiesModel citiesModel;
    @EJB
    private HeadquartersModel headquarterModel;
    
    private Citizens citizen;    

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

    public Citizens getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizens citizen) {
        this.citizen = citizen;
        this.citizen.getCitizenTypeId().setId("CITIZN");
        this.citizen.setPassword("votante");
    }

    //Process & new Instance of the Bean
    public CitizenBean() {
        this.citizen = new Citizens();
        this.citizen.setCitizenTypeId(new CitizenTypes());
        this.citizen.setHeadquarterId(new Headquarters());
    }

    public List<Departments> getDepar() {
        return departmentModel.getDepartments();
    }

    public List<Cities> citiesForDepartment() {
        return citiesModel.listCitiesforDepartment(this.idDepartment);
    }

    public List<Headquarters> headquartersForCities() {
        return headquarterModel.listHeadquartersForCity(this.idCity);
    }
    public void onloadRequest() {
        String code = Utilities.getParam("codigo");  
        this.citizen = this.citizenModel.getCitizen(Integer.parseInt(code));
        this.idDepartment = this.citizen.getHeadquarterId().getCityId().getDeparmentId().getId();
        this.idCity = this.citizen.getHeadquarterId().getCityId().getId();
    }
    public void save() throws ParseException {
        if (!this.citizenModel.existsDui(citizen)) {
            if (Validacion.esDui(citizen.getDui())) {
                if (Validacion.esNombrePersona(citizen.getName()) && Validacion.esNombrePersona(citizen.getLastname())) {
                    if (Validacion.esDireccion(citizen.getAdress())) {
                            this.citizen.setPassword(null);
                            //insertando
                            this.citizen.setCitizenTypeId(this.citizenTypesModel.getCitizenTypes("CITIZN"));
                            this.citizen.setHeadquarterId(this.headquarterModel.getHeadquarter(this.citizen.getHeadquarterId().getId()));
                            this.citizen.setState(Short.parseShort("1"));
                            if (this.citizenModel.insertCitizen(citizen)) {
                                Utilities.AddMessage("exito", "El Ciudadano fue registrado!!");
                                Utilities.redirect("/faces/employeeRnpn/Citizens.xhtml");
                                } else {
                                    Utilities.addMessageError("Error_Insert", "No se ha podido registrar al ciudadano");
                                }
                    } else {
                        Utilities.addMessageError("Error_Fecha", "Algunos caracteres son invalidos de la direccion");
                    }
                } else {
                    Utilities.addMessageError("Error_Fecha", "Por favor ingrese un nombre o apellido correcto");
                }
            } else {
                Utilities.addMessageError("Error_Fecha", "El DUI no posee un formato correcto");
            }
        } else {
            Utilities.addMessageError("Error_Fecha", "El DUI ingresado ya existe");
        }
    }

    public List<Citizens> listCitizens() {
        return this.citizenModel.listCitizenNormal("CITIZN");
    }

    
    public void update() {
        if (Validacion.esDui(citizen.getDui())) {
                if (Validacion.esNombrePersona(citizen.getName()) && Validacion.esNombrePersona(citizen.getLastname())) {
                    if (Validacion.esDireccion(citizen.getAdress())) {
                            this.citizen.setPassword(null);
                            //modificando
                            this.citizen.setCitizenTypeId(this.citizenTypesModel.getCitizenTypes("CITIZN"));
                            this.citizen.setHeadquarterId(this.headquarterModel.getHeadquarter(this.citizen.getHeadquarterId().getId()));
                            if (this.citizenModel.editCitizen(citizen)) {
                                Utilities.AddMessage("exito", "El Ciudadano fue modificado!!");
                                Utilities.redirect("/faces/employeeRnpn/Citizens.xhtml");
                                } else {
                                    Utilities.addMessageError("Error_Insert", "No se ha podido modificar al ciudadano");
                                }
                    } else {
                        Utilities.addMessageError("Error_Fecha", "Algunos caracteres son invalidos de la direccion");
                    }
                } else {
                    Utilities.addMessageError("Error_Fecha", "Por favor ingrese un nombre o apellido correcto");
                }
            } else {
                Utilities.addMessageError("Error_Fecha", "El DUI no posee un formato correcto");
            }
    }
    
    public void enabledCitizens(Citizens citizen) {
        addMessage("Empleado RNPN", "Ciudadano Inhabilitado");
        this.executeEnable(citizen);
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String redirect(){    
        return "/employeeRnpn/editarCitizen.xhtml";
    }
    
    public void executeEnable(Citizens citizen){
        byte state = 0;
        this.citizen = citizen;
        this.citizen.setState(Short.valueOf(state));
        if(this.citizenModel.enableCitizen(this.citizen)){
            Utilities.AddMessage("exito", "El Ciudadano fue deshabilitado!!");
            Utilities.redirect("/faces/employeeRnpn/Citizens.xhtml");
        }
        Utilities.redirect("/faces/employeeRnpn/Citizens.xhtml");
    }
}
