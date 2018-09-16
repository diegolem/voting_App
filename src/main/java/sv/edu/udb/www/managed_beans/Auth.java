/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.Entities.Citizen;
import sv.edu.udb.www.Model.CitizenModel;

/**
 *
 * @author pc
 */
@SessionScoped
@ManagedBean(name="Auth")
public class Auth implements Serializable {

    @EJB
    private CitizenModel citizenModel;
    
    private Citizen citizen = new Citizen();
    
    public Auth() { }
    
    public String login(){
        if (this.citizenModel.exists(this.citizen)) {
            this.citizen.setLogged(true);
            return "faces/home.xhtml";
        }
        
        return "faces/login.xhtml";
    }
    
    @Produces
    @Named("attrname")
    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen auth) {
        this.citizen = auth;
    }
    
    public boolean isLogged(){
        return this.citizen.isLogged();
    }
}
