/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Model.PoliticGroupsModel;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Model.PoliticGroupsModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "politicGroupBean")
@ViewScoped
public class PoliticGroupBean implements Serializable {

    @EJB
    private PoliticGroupsModel politicGroupModel;

    /**
     * Creates a new instance of PoliticGroupBean
     */
    
    public List<PoliticGroups> allPositicGroupWithCandidates(){
        return this.politicGroupModel.listPoliticGroupsWithCandidates();
    }
   
    private PoliticGroups politicGroup;
    
    private Part image;
    
    private String fileName;
    
    @ManagedProperty("#{param.id}")
    private int idRequest;

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }
    
    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    
    public PoliticGroups getPoliticGroup() {
        return politicGroup;
    }
    
    public void setPoliticGroup(PoliticGroups politicGroup) {
        this.politicGroup = politicGroup;
    }
    
    public PoliticGroupBean() {
        this.politicGroup = new PoliticGroups();
    }

    public void onloadRequest() {
        this.politicGroup = this.politicGroupModel.getPoliticGroup(this.idRequest);
        this.fileName = this.politicGroup.getPhoto();
    }
    public void doUpload(){
        try{
           InputStream in = this.image.getInputStream();
           if(Validacion.esNombreImagen(this.image.getSubmittedFileName())){
           File f = new File(Utilities.getPath("/images/") + "/" + this.image.getSubmittedFileName());
           this.politicGroup.setPhoto(this.image.getSubmittedFileName());
           f.createNewFile();
           FileOutputStream out = new FileOutputStream(f);
           byte[] buffer = new byte[1024];
           int length;
           while((length = in.read(buffer)) > 0){
               out.write(buffer, 0, length);
           }
           out.close();
           in.close();  
           }else{
               Utilities.addMessageError("Error_Archivo", "Ingrese un archivo valido");
           }
           
        }catch(IOException ex){
            ex.printStackTrace();
            Utilities.addMessageError("Error_Archivo", "Ha ocurrido un error con el archivo");
        }
    }
    public void save() {
        if (Validacion.esNombreEmpresa(this.politicGroup.getName())) {
            if (this.politicGroup.getAcronym().length() >= 3 && this.politicGroup.getAcronym().length() <= 8) {
                if (Validacion.esDireccion(this.politicGroup.getDescription())) {
                    this.doUpload();
                        if (!this.politicGroupModel.existAcronym(politicGroup) && !this.politicGroupModel.existName(politicGroup)) {
                            if (this.politicGroupModel.insertPoliticGroup(this.politicGroup)) {
                                Utilities.AddMessage("exito", "El Partido politico fue ingresado!!");
                                Utilities.redirect("/faces/generalAdministration/PoliticGroup.xhtml");
                            } else {
                                this.eliminateImage(new File(Utilities.getPath("/images/") + "/" + this.politicGroup.getPhoto()));
                                Utilities.addMessageError("Error_Insert", "No se ha podido ingresar el partido politico");
                            }
                        } else {
                            Utilities.addMessageError("Error_Existencia", "El nombre o el acronimo ya existen");
                        }
                } else {
                    Utilities.addMessageError("Error_Direccion", "Algunos caracteres no estan permitidos en su descripcion");
                }
            } else {
                Utilities.addMessageError("Error_Acronimo", "La longitud del acronimo debe estar entre 3 y 8");
            }
        } else {
            Utilities.addMessageError("Error_Nombre", "El nombre no es valido");
        }
    }
    
    public List<PoliticGroups> listPoliticGroups(){
        return this.politicGroupModel.listPoliticGroups();
    }

  
    public void eliminateImage(File fichero){
        if(!fichero.exists()){
            Utilities.addMessageError("Error_Archivo", "El archivo con nombre " + fichero.getName() + " no existe");
        }else{
            fichero.delete();
        }
    }
    public void update() {
        if (Validacion.esNombreEmpresa(this.politicGroup.getName())) {
            if (this.politicGroup.getAcronym().length() >= 3 && this.politicGroup.getAcronym().length() <= 8) {
                if (Validacion.esDireccion(this.politicGroup.getDescription())) {
                            if(this.image == null){
                                if (this.politicGroupModel.editPoliticGroup(this.politicGroup)) {
                                Utilities.AddMessage("exito", "El partido politico fue modificado!!");
                                Utilities.redirect("/faces/generalAdministration/PoliticGroup.xhtml");
                                } else {
                                    Utilities.addMessageError("Error_Insert", "No se ha podido ingresar el partido politico");
                                }
                            }else{
                                this.eliminateImage(new File(Utilities.getPath("/images/") + "/" + this.politicGroup.getPhoto()));
                                this.doUpload();
                                if (this.politicGroupModel.editPoliticGroup(this.politicGroup)) {
                                Utilities.AddMessage("exito", "El partido politico fue modificado!!");
                                Utilities.redirect("/faces/generalAdministration/PoliticGroup.xhtml");
                                } else {
                                    Utilities.addMessageError("Error_Insert", "No se ha podido ingresar el partido politico");
                                }
                            }
                } else {
                    Utilities.addMessageError("Error_Direccion", "Algunos caracteres no estan permitidos en su descripcion");
                }
            } else {
                Utilities.addMessageError("Error_Acronimo", "La longitud del acronimo debe estar entre 3 y 8");
            }
        } else {
            Utilities.addMessageError("Error_Nombre", "El nombre no es válido");
        }
    }

    public void eliminateFlash() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.clear();
    }

    public void redirect() {
        Utilities.redirect("/faces/generalAdministration/editPoliticGroup.xhtml");
    }
    public void asignPhotoPoliticGroup(FileUploadEvent event){
        FacesMessage message = new FacesMessage("Exito", event.getFile().getFileName() + "Archivo cargado");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void delete(PoliticGroups politicGroup) {
        this.politicGroup = politicGroup;
        this.eliminateImage(new File(Utilities.getPath("/images/") + "/" + this.politicGroup.getPhoto()));
        if (this.politicGroupModel.deletePoliticGroup(this.politicGroup)) {
            Utilities.AddMessage("exito", "El partido politico fue eliminado!!");
            Utilities.redirect("/faces/generalAdministration/PoliticGroup.xhtml");
        }
        Utilities.redirect("/faces/generalAdministration/PoliticGroup.xhtml");
    }
}
