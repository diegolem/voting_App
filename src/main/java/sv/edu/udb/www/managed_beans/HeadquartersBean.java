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
import javax.faces.view.ViewScoped;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Model.HeadquartersModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "headquartersBean")
@ViewScoped
public class HeadquartersBean  implements Serializable {
    @EJB
    private HeadquartersModel headquarterModel;    
    /**
     * Creates a new instance of HeadquartersBean
     */
    public HeadquartersBean() {
    }
    public List<Headquarters> listHeadquarterForCity(int idCity){
        return headquarterModel.listHeadquartersForCity(idCity);
    }
    
}
