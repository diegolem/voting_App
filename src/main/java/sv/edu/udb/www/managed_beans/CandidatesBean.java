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
import sv.edu.udb.www.Entities.PresidencialCandidates;
import sv.edu.udb.www.Model.PresidencialCandidatesModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "candidatesBean")
@ViewScoped
public class CandidatesBean implements Serializable {

    @EJB
    private PresidencialCandidatesModel presidencialCandidatesModel;
    @EJB
    private CitizenTypesModel citizenTypesModel;
    @EJB
    private PoliticGroupsModel politicGroupsModel;
    @EJB
    private HeadquartersModel headquartersModel;
    @EJB
    private CitizenModel citizenModel;
    @EJB
    private CandidatesModel candidatesModel;
    @EJB
    private CandidatesForCitiesModel candidatesForCitiesModel;

    // ///////////////////////
    private int idPoliticGroup;
    
    private boolean editProcees;
    // /////////////////////////////////////////

    @ManagedProperty("#{param.id}")
    private int idRequest;
    private int idRequestCitizens;
    private int idCandidate;
    
    private Citizens citizens;
    private Candidates candidates;
    private CandidatesForCities candidatesForCities;
    private PresidencialCandidates presidencialCandidates;
    
    private String folder = "resources/images";
    private String urlPhotoRequest;
    private Part uploadedFile;
    
    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public CandidatesBean() {
        this.citizens = new Citizens();
        this.citizens.setHeadquarterId(new Headquarters());
        this.candidates = new Candidates();
        this.candidates.setPoliticGroupId(new PoliticGroups());

        this.candidatesForCities = new CandidatesForCities();
        this.presidencialCandidates = new PresidencialCandidates();
    }

    // ////////////////////////////
    public List<Candidates> allCandidatesForGroupId() {
        return this.candidatesModel.listCandidatesForPoiliticGroup(this.idPoliticGroup);
    }

    public List<Candidates> allCandidatesForGroupIdWithProccess() {
        return this.candidatesModel.listCandidatesForPoiliticGroupWithProcess(this.idPoliticGroup);
    }

    public void addCandidateProcessForId(int id) {
        this.idCandidate = id;
        this.editProcees = false;
        
        this.candidates = this.candidatesModel.getCandidates(id);
    }

    public void editCandidateProcessCityForId(int id, int idCityCandidate) {
        this.idCandidate = id;
        this.editProcees = true;

        this.candidatesForCities = this.candidatesForCitiesModel.getCandidatesCities(idCityCandidate);
        this.candidates = this.candidatesModel.getCandidates(id);
    }

    public void editCandidateProcessPresidentialForId(int id, int idPresidentialCandidate) {
        this.idCandidate = id;
        this.editProcees = true;
        
        this.candidates = this.candidatesModel.getCandidates(id);
        this.presidencialCandidates = this.presidencialCandidatesModel.getPresidencialCandidate(idPresidentialCandidate);
    }
    
    public boolean isEditProcees() {
        return editProcees;
    }

    public void setEditProcees(boolean editProcees) {
        this.editProcees = editProcees;
    }
    
    // CRUD procesos por ciudad
    public void saveCadidateProcessesByCity() {
        this.candidatesForCities.setCandidateId(this.candidatesModel.getCandidates(idCandidate));

        if (!editProcees)
            this.candidatesForCitiesModel.insertCandidatesCities(candidatesForCities);
        else
            this.candidatesForCitiesModel.editCandidatesCities(candidatesForCities);
    }
    
    public void deleteCadidateProcessesByCity(){
        this.candidatesForCitiesModel.deleteCandidatesCities(this.candidatesForCities.getId());
    }
    
    // CRUD procesos precidenciales
    public void saveCadidateProcessesPresidential() {
        this.presidencialCandidates.setCandidatesId(this.candidatesModel.getCandidates(idCandidate));

        if (!editProcees)
            if (!this.presidencialCandidatesModel.insertPresidencialCandidate(presidencialCandidates))
                Utilities.addMessageFlash("error", "No se ha podido enlazar con el proceso presidencial.");
            else
                Utilities.addMessageFlash("exito", "Se ha establecido el proceso");
        else
            if (!this.presidencialCandidatesModel.editPresidencialCandidate(presidencialCandidates))
                Utilities.addMessageFlash("error", "No se ha podido enlazar con el proceso presidencial.");
            else
                Utilities.addMessageFlash("exito", "Se ha modificado el proceso");
    }
    
    public void deleteCadidateProcessesPresidential(){
        if (!this.presidencialCandidatesModel.deletePresidencialCandidate(this.presidencialCandidates.getId()))
            Utilities.addMessageFlash("error", "No se ha podido retira del proceso presidencial.");
        else
            Utilities.addMessageFlash("exito", "Se ha retirado el candidato del proceso");
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
        Candidates candidate = this.candidatesModel.getCandidatesWithProcess(this.idCandidate);

        if (candidate != null && !candidate.hasElectoralProcessActiveProcess() && !candidate.hasPresidencialCandidatesProcessActive())
            this.candidatesModel.deleteCandidates(this.idCandidate);
        else 
            Utilities.addMessageFlash("error", "El candidato esta en medio de un proceso electoral");
    }

    public void saveId(int id) {
        this.idCandidate = id;
    }

    public void update() {
        this.folder = Utilities.getPath("/resources/images");

        // Obtenemos el candidato actual
        Candidates originalCandidates = this.candidatesModel.getCandidates(idRequest);

        originalCandidates.getCitizenId().setDui(this.citizens.getDui());
        originalCandidates.getCitizenId().setName(this.citizens.getName());
        originalCandidates.getCitizenId().setLastname(this.citizens.getLastname());
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

    public PresidencialCandidates getPresidencialCandidates() {
        return presidencialCandidates;
    }

    public void setPresidencialCandidates(PresidencialCandidates presidencialCandidates) {
        this.presidencialCandidates = presidencialCandidates;
    }
}
