/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "candidatesBean")
@ViewScoped
public class CandidatesBean  implements Serializable {

    public CandidatesBean() {
    }
    
}
