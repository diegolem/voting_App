/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import sv.edu.udb.www.Entities.Candidates;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Model.CandidatesForCitiesModel;
import sv.edu.udb.www.Model.CandidatesModel;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenTypesModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Model.PoliticGroupsModel;
import sv.edu.udb.www.Utilities;
import sv.edu.udb.www.Validacion;
import sv.edu.udb.www.Entities.CandidatesForCities;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "candidatesBean")
@ViewScoped
public class CandidatesBean implements Serializable {

    @EJB
    private CandidatesForCitiesModel candidatesForCitiesModel;

    // ///////////////////////
    private int idPoliticGroup;
    private CandidatesForCities candidatesForCities;
    // /////////////////////////////////////////
    
    @EJB
    private CitizenTypesModel citizenTypesModel;

    @EJB
    private PoliticGroupsModel politicGroupsModel;

    @EJB
    private HeadquartersModel headquartersModel;

    private int idCandidate;
    
    private Citizens citizens;
    private Candidates candidates;

    private Part uploadedFile;
    private String folder = "resources/images";

    @ManagedProperty("#{param.id}")
    private int idRequest;

    private String urlPhotoRequest;
    private int idRequestCitizens;

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    @EJB
    private CitizenModel citizenModel;

    @EJB
    private CandidatesModel candidatesModel;

    public CandidatesBean() {
        this.citizens = new Citizens();
        this.citizens.setHeadquarterId(new Headquarters());
        this.candidates = new Candidates();
        this.candidates.setPoliticGroupId(new PoliticGroups());
        
        this.candidatesForCities = new CandidatesForCities();
    }

    // ////////////////////////////
    
    public List<Candidates> allCandidatesForGroupId(){
        return this.candidatesModel.listCandidatesForPoiliticGroup(this.idPoliticGroup);
    }
    
    public void candidateForId(int id){
        this.idCandidate = id;
        this.candidates = this.candidatesModel.getCandidates(id);
    }
    
    // ///////////////////////////////////
    
    public Citizens getCitizens() {
        return citizens;
    }

    public void setCitizens(Citizens citizens) {
        this.citizens = citizens;
    }

    public void handleFileUpload(AjaxBehaviorEvent event) {
        System.out.println("file size: " + uploadedFile.getSize());
        System.out.println("file type: " + uploadedFile.getContentType());
        System.out.println("file info: " + uploadedFile.getHeader("Content-Disposition"));
        System.out.println("file url: " + Utilities.getPath("resources/images"));
    }

    public void delete() {
        Candidates candidate = this.candidatesModel.getCandidates(this.idCandidate);

        if (candidate != null) {
            this.candidatesModel.deleteCandidates(this.idCandidate);
        }
    }
    
    public void saveId(int id){
        this.idCandidate = id;
    }

    public void update() {
        this.folder = Utilities.getPath("/resources/images");

        // Obtenemos el candidato actual
        Candidates originalCandidates = this.candidatesModel.getCandidates(idRequest);

        originalCandidates.getCitizenId().setDui(this.citizens.getDui());
        originalCandidates.getCitizenId().setName(this.citizens.getName());
        originalCandidates.getCitizenId().setAdress(this.citizens.getAdress());
        originalCandidates.getCitizenId().setBirthdate(this.citizens.getBirthdate());

        originalCandidates.setPoliticGroupId(this.politicGroupsModel.getPoliticGroup(this.candidates.getPoliticGroupId().getId()));
        originalCandidates.getCitizenId().setHeadquarterId(this.headquartersModel.getHeadquarter(this.citizens.getHeadquarterId().getId()));

        // Primero añadiremos todos los datos importantes
        if (Validacion.esDui(originalCandidates.getCitizenId().getDui())) {
            if (!this.citizenModel.existsWithOtherDui(originalCandidates.getCitizenId())) {
                if (Utilities.getYears(originalCandidates.getCitizenId().getBirthdate()) > 18) {

                    boolean fileError = false;

                    if (uploadedFile != null) {
                        try (InputStream input = uploadedFile.getInputStream()) {
                            String fileName = uploadedFile.getSubmittedFileName();
                            Files.copy(input, new File(folder, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            originalCandidates.setPhoto(fileName);
                        } catch (Exception e) {
                            fileError = true;
                            Utilities.addMessageError("Error", "No se ha podido registrar el candidato");
                        }
                    } else {
                        originalCandidates.setPhoto(this.urlPhotoRequest);
                    }

                    if (!fileError) {
                        if (this.citizenModel.editCitizen(originalCandidates.getCitizenId())) {
                            if (this.candidatesModel.editCandidates(originalCandidates)) {
                                Utilities.redirect("/faces/generalAdministration/candidates.xhtml");
                            } else {
                                Utilities.addMessageError("Error", "No se ha podido modificar el candidato");
                            }
                        } else {
                            Utilities.addMessageError("Error", "No se ha podido modificar el ciudadano");
                        }
                    } else {
                        Utilities.addMessageError("Error", "Error inesperado");
                    }
                } else {
                    Utilities.addMessageError("Error", "El cantidado debe de ser mayor de edad");
                }
            } else {
                Utilities.addMessageError("Error", "El dui ya existe");
            }
        } else {
            Utilities.addMessageError("Error", "Debe de ingresar alfun dui valido");
        }
    }

    public void save() {
        this.folder = Utilities.getPath("/resources/images");

        if (Validacion.esDui(this.citizens.getDui())) {
            if (!this.citizenModel.existsWithDui(citizens)) {
                if (Utilities.getYears(this.citizens.getBirthdate()) > 18) {

                    boolean errorPhoto = false;

                    if (uploadedFile != null) {
                        try (InputStream input = uploadedFile.getInputStream()) {
                            String fileName = uploadedFile.getSubmittedFileName();
                            Files.copy(input, new File(folder, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                            this.candidates.setPhoto(fileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                            errorPhoto = true;
                            Utilities.addMessageError("Error", "No se ha podido registrar el cantitado");
                        }
                    } else {
                        this.candidates.setPhoto("Default-user.png");
                    }

                    if (!errorPhoto) {
                        this.citizens.setPassword(this.citizens.getDui());
                        this.citizens.setCitizenTypeId(this.citizenTypesModel.getCitizenTypes("CITIZN"));
                        this.citizens.setId(null);

                        if (this.citizenModel.insertCitizen(this.citizens)) {
                            this.candidates.setCitizenId(this.citizens);
                            if (this.candidatesModel.insertCandidates(candidates)) {
                                Utilities.redirect("/faces/generalAdministration/candidates.xhtml");
                            } else {
                                Utilities.addMessageError("Error", "No se ha podido registrar el candidato");
                            }
                        } else {
                            Utilities.addMessageError("Error", "No se ha podido registrar el ciudadano");
                        }
                    } else {
                        Utilities.addMessageError("Error", "No se ha podido registrar el ciudadano");
                    }

                } else {
                    Utilities.addMessageError("Error", "El cantidado debe de ser mayor de edad");
                }
            } else {
                Utilities.addMessageError("Error", "El dui ya existe");
            }
        } else {
            Utilities.addMessageError("Error", "Debe de ingresar alfun dui valido");
        }

    }

    public List<Headquarters> allHeadquarters() {
        return headquartersModel.listHeadquarters();
    }

    public List<PoliticGroups> allPoliticGroups() {
        return politicGroupsModel.listPoliticGroups();
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }

    public List<Candidates> allCandidates() {
        return this.candidatesModel.listCandidates();
    }

    public String getUrlPhotoRequest() {
        return urlPhotoRequest;
    }

    public void setUrlPhotoRequest(String urlPhotoRequest) {
        this.urlPhotoRequest = urlPhotoRequest;
    }

    public void onLoadRequest() {
        this.candidates = this.candidatesModel.getCandidates(idRequest);
        this.citizens = this.candidates.getCitizenId();

        // Obtenemos los datos importantes
        this.idRequestCitizens = this.candidates.getCitizenId().getId();
        this.urlPhotoRequest = this.candidates.getPhoto();
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public int getIdRequestCitizens() {
        return idRequestCitizens;
    }

    public void setIdRequestCitizens(int idRequestCitizens) {
        this.idRequestCitizens = idRequestCitizens;
    }

    public int getIdCandidate() {
        return idCandidate;
    }

    public void setIdCandidate(int idCandidate) {
        this.idCandidate = idCandidate;
    }

    public int getIdPoliticGroup() {
        return idPoliticGroup;
    }

    public void setIdPoliticGroup(int idPoliticGroup) {
        this.idPoliticGroup = idPoliticGroup;
    }

    public CandidatesForCities getCandidatesForCities() {
        return candidatesForCities;
    }

    public void setCandidatesForCities(CandidatesForCities candidatesForCities) {
        this.candidatesForCities = candidatesForCities;
    }

    public void saveCadidateProcesses(){
        this.candidatesForCities.setCandidateId(this.candidatesModel.getCandidates(idCandidate));
        this.candidatesForCitiesModel.insertCandidatesCities(candidatesForCities);
    }
}
