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
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Model.PoliticGroupsModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "politicGroupBean")
@ViewScoped
public class PoliticGroupBean  implements Serializable {

    @EJB
    private PoliticGroupsModel politicGroupsModel;

    /**
     * Creates a new instance of PoliticGroupBean
     */
    public PoliticGroupBean() {
    }
    
    public List<PoliticGroups> allPositicGroupWithCandidates(){
        return this.politicGroupsModel.listPoliticGroupsWithCandidates();
    }
    
}
