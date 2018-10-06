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
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author pc
 */
@SessionScoped
@ManagedBean(name = "Auth")
public class Auth implements Serializable {

    @EJB
    private CitizenModel citizenModel;

    private Citizens citizen = new Citizens();

    public Auth() {
    }

    public String login() {

        if (Validacion.isEmpty(this.citizen.getDui()) || Validacion.isEmpty(this.citizen.getPassword())) {
            Utilities.addMessageError("Error_Login_Empty", "Ambos campos son obligatorios");
        } else {
            if (Validacion.esDui(this.citizen.getDui())) {
                if (this.citizenModel.exists(this.citizen)) {
                    this.citizenModel.pullForDui(this.citizen);
                    this.citizen.setLogged(true);
                    return "/faces/home.xhtml?faces-redirect=true";
                } else {
                    Utilities.addMessageError("Error_Login", "Las credenciales no son correctas");
                }
            } else {
                Utilities.addMessageError("Error_Login_Dui", "Debe ingresar un dui valido");
            }
        }

        return "/faces/login.xhtml?faces-redirect=true";
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/login.xhtml?faces-redirect=true";
    }

    @Produces
    @Named("attrname")
    public Citizens getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizens auth) {
        this.citizen = auth;
    }

    public boolean isLogged() {
        return this.citizen.isLogged();
    }

    public boolean isGeneralAdministration() {
        return (this.citizen.isEmpty()) ? false : this.citizen.getCitizenTypeId().getId().equals("ADMGEN");
    }

    public boolean isDepartmentAdministration() {
        return (this.citizen.isEmpty()) ? false : this.citizen.getCitizenTypeId().getId().equals("ADMDEP");
    }

    public boolean isEmployeeRnpn() {
        return (this.citizen.isEmpty()) ? false : this.citizen.getCitizenTypeId().getId().equals("EMRNPN");
    }

    public boolean isPresidentJrv() {
        return (this.citizen.isEmpty()) ? false : this.citizen.getCitizenTypeId().getId().equals("PREJRV");
    }

    public Jrv jrvActive() {

        if (this.isPresidentJrv()) {

            List jrvCitizenes;
            if (this.citizen.getJrvCitizenCollection() instanceof List) {
                jrvCitizenes = (List) this.citizen.getJrvCitizenCollection();
            } else {
                jrvCitizenes = new ArrayList(this.citizen.getJrvCitizenCollection());
            }

            JrvCitizen jrvCitizen = (JrvCitizen) jrvCitizenes.get(jrvCitizenes.size() - 1);

            //if (jrvCitizen.getJrvId().getElectoralProcessId().getElectoralProcessStatusId().getId() != 4)
            return jrvCitizen.getJrvId();

        }

        return new Jrv();
    }

}
