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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.JrvCitizenTypes;
import sv.edu.udb.www.Model.JrvCitizenModel;
import sv.edu.udb.www.Model.JrvCitizenTypesModel;
import sv.edu.udb.www.Model.JrvModel;
import sv.edu.udb.www.Utilities;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "jrvCitizenTypesBean")
@ViewScoped
public class JrvCitizenTypesBean implements Serializable {

    @EJB
    private JrvModel jrvModel;
    @EJB
    private JrvCitizenTypesModel jrvCitizenTypesModel;

    private JrvCitizenTypes type;

    public JrvCitizenTypes getType() {
        return type;
    }

    public void setType(JrvCitizenTypes type) {
        this.type = type;
    }

    public List<JrvCitizenTypes> list() {
        return this.jrvCitizenTypesModel.listJrvCitizenTypes();
    }

    public List<JrvCitizenTypes> listUnique() {
        return this.jrvCitizenTypesModel.listJrvCitizenTypesUnique();
    }
    
    public List<JrvCitizenTypes> listWithoutUsing(int idJvr){
        List<JrvCitizenTypes> types = this.jrvCitizenTypesModel.listJrvCitizenTypes();
        List<JrvCitizenTypes> typesFinal = new ArrayList();
        Jrv jrv = this.jrvModel.getJrv(idJvr);
        
        if (jrv == null)
            return typesFinal;
        
        int vocalSize = 3;
        
        for(JrvCitizenTypes type: types){
            if (type.unique()) {
                boolean exist = false;
                int vocalCount = 0;
                for(JrvCitizen jrvCitizen : jrv.getJrvCitizenCollection()){
                    if(jrvCitizen.getJrvCitizenTypeId().equals(type)){
                        if (type.getId() == 3) {
                            vocalCount++;
                            
                            if (vocalCount == vocalSize){
                                exist = true;
                                break;
                            }
                        } else {
                            exist = true;
                            break;
                        }
                    }
                }
                if (!exist)
                    typesFinal.add(type);
            }
            else
                typesFinal.add(type);
        }
        
        return typesFinal;
    }
    
    public JrvCitizenTypesBean() {
        this.type = new JrvCitizenTypes();
    }

    public void save() {

        if (this.jrvCitizenTypesModel.insertJrvCitizenTypes(this.type)) {
            Utilities.addMessageFlash("exito", "Se ha añadido un nuevo tipo de Junta");
            Utilities.redirect("/faces/departmentalAdministration/typeJrv.xhtml");
        } else {
            Utilities.addMessageError("error", "No se ha podido registrar el tipo de JRV");
        }
    }

    public void update() {

        this.type.setName(Utilities.getRequestValue("frm:nameEdit"));
        this.type.setDescription(Utilities.getRequestValue("frm:descEdit"));
        this.type.setId(Integer.parseInt(Utilities.getRequestValue("frm:idEdit")));

        if (this.jrvCitizenTypesModel.editJrvCitizenTypes(type)) {
            Utilities.addMessageFlash("exito", "Se ha actualizado el tipo");
        } else {
            Utilities.addMessageFlash("error", "No se ha podido axtualizar la junta");
        }

    }

    public void delete() {

        JrvCitizenTypes item = this.jrvCitizenTypesModel.getJrvCitizenTypes(Integer.parseInt(Utilities.getRequestValue("frm:idDelete")));

            if (item.canDelete()) {
                if (this.jrvCitizenTypesModel.deleteJrvCitizenTypes(item.getId())) {
                    Utilities.addMessageFlash("exito", "Se ha eliminado el tipo de junta");
                } else {
                    Utilities.addMessageFlash("error", "No se ha podido eliminar el tipo de junta");
                }
            } else {
                Utilities.addMessageFlash("error", "El tipo de junta posee datos relacionados");
            }
        
    }

    public void selectTypeJrv(int id) {
        this.type = this.jrvCitizenTypesModel.getJrvCitizenTypes(id);
    }
}
