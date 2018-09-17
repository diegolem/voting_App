/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Model.CitizenModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "citizenBean")
@ViewScoped
public class CitizenBean  implements Serializable {

    @EJB
    private CitizenModel citizenModel;
    
    public CitizenBean() {}
}
