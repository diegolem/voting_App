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
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.Entities.ElectoralProcessTypes;
import sv.edu.udb.www.Model.ElectoralProcessTypesModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "electoralProcessTypesBean")
@ViewScoped
public class ElectoralProcessTypesBean  implements Serializable {

    @EJB
    private ElectoralProcessTypesModel electoralProcessTypesModel;

    
    /**
     * Creates a new instance of ElectoralProcessTypesBean
     */
    public ElectoralProcessTypesBean() {
    }
    
    public List<ElectoralProcessTypes> allElectoralProcessTypes(){
        return electoralProcessTypesModel.listElectoralProcessTypes();
    }
}
