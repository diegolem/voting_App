/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "candidatesForCitiesBean")
@ViewScoped
public class CandidatesForCitiesBean  implements Serializable {

    /**
     * Creates a new instance of CandidatesForCitiesBean
     */
    public CandidatesForCitiesBean() {
    }
    
}
