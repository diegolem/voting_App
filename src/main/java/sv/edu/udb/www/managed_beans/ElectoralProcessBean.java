/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.ElectoralProcessTypes;
import sv.edu.udb.www.Model.ElectoralProcessModel;
import sv.edu.udb.www.Model.ElectoralProcessStatusModel;
import sv.edu.udb.www.Utilities;
import org.primefaces.event.CloseEvent;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.PoliticGroupVotes;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Model.JrvCitizenModel;
import sv.edu.udb.www.Model.JrvCitizenTypesModel;
import sv.edu.udb.www.Model.JrvModel;
import sv.edu.udb.www.Model.PoliticGroupVotesModel;
import sv.edu.udb.www.Model.PoliticGroupsModel;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "electoralProcessBean")
@ViewScoped
public class ElectoralProcessBean implements Serializable {

    @EJB
    private PoliticGroupsModel politicGroupsModel;
    @EJB
    private PoliticGroupVotesModel politicGroupVotesModel;
    @EJB
    private JrvCitizenTypesModel jrvCitizenTypesModel;
    @EJB
    private CitizenModel citizenModel;
    @EJB
    private JrvCitizenModel jrvCitizenModel;
    @EJB
    private HeadquartersModel headquartersModel;
    @EJB
    private ElectoralProcessStatusModel electoralProcessStatusModel;
    @EJB
    private JrvModel jrvModel;
    @EJB
    private ElectoralProcessModel electoralProcessModel;

    private ElectoralProcess electoralProcess;
    @ManagedProperty("#{param.id}")
    private int idRequest;

    private boolean avalibleActualProcess;
    private int idStatus;
    private int idHeadquarter;
    private int idHeadquarterActual;

    public boolean isAvalibleActualProcess() {
        return avalibleActualProcess;
    }

    public void setAvalibleActualProcess(boolean avalibleActualProcess) {
        this.avalibleActualProcess = avalibleActualProcess;
    }

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public boolean processDateIsNow() {
        return false;
    }

    /**
     * Creates a new instance of ElectoralProcessBean
     */
    public ElectoralProcessBean() {
        this.electoralProcess = new ElectoralProcess();
        this.electoralProcess.setElectoralProcessTypesId(new ElectoralProcessTypes());
    }

    public ElectoralProcess getElectoralProcess() {
        return electoralProcess;
    }

    public void setElectoralProcess(ElectoralProcess electoralProcess) {
        this.electoralProcess = electoralProcess;
    }

    public List<ElectoralProcess> allElectoralProcessByEndDate() {
        return this.electoralProcessModel.listElectoralProcessByEndDate();
    }

    public List<ElectoralProcess> allElectoralProcessByEndDateDepartamental() {
        return this.electoralProcessModel.listElectoralProcessByEndDateDepartamental();
    }
    
    public List<ElectoralProcess> allElectoralProcessByEndDateDepartamental(int idPoliticGroup) {
        List<ElectoralProcess> electoral = this.electoralProcessModel.listElectoralProcessByEndDateDepartamental();
        List<ElectoralProcess> processes = new ArrayList();
        
        for (ElectoralProcess process : electoral){
    
            if (process.getPresidencialCandidatesCollection().size() > 0)
                for(PresidencialCandidates presidencialCandidate : process.getPresidencialCandidatesCollection()){
                    if (presidencialCandidate.getCandidatesId().getPoliticGroupId().getId() != idPoliticGroup)
                        processes.add(process);
                }
            else
                processes.add(process);
        
        }
        
        return processes;
    }

    public List<ElectoralProcess> allElectoralProcessByEndDatePresidential() {
        return this.electoralProcessModel.listElectoralProcessByEndDatePresidential();
    }

    public List<ElectoralProcess> allElectoralProcessByEndDatePresidential(int idPoliticGroup) {
        List<ElectoralProcess> electoral = this.electoralProcessModel.listElectoralProcessByEndDatePresidential();
        List<ElectoralProcess> processes = new ArrayList();
        
        for (ElectoralProcess process : electoral){
    
            if (process.getPresidencialCandidatesCollection().size() > 0)
                for(PresidencialCandidates presidencialCandidate : process.getPresidencialCandidatesCollection()){
                    if (presidencialCandidate.getCandidatesId().getPoliticGroupId().getId() != idPoliticGroup)
                        processes.add(process);
                }
            else
                processes.add(process);
        
        }
        
        return processes;
    }

    
    public void reloadJrv() {
        Auth auth = Utilities.getUserAuth();

        if (auth != null) {
            int id = Integer.parseInt(Utilities.getRequestValue("frm:electoralResetProcessId"));

            ElectoralProcess process = this.electoralProcessModel.getElectoralProcess(id);

            if (process != null) {
                int actual = Integer.parseInt(Utilities.getRequestValue("frm:headquarterResetActual"));
                
                change(auth, process, actual, actual);
            }
        }

    }

    private void change(Auth auth, ElectoralProcess process, int actual, int now) {
        List<Jrv> jrvs = this.jrvModel.listJrvByProcessAndHeadquarter(actual, process.getId());

        boolean isOk = true;

        for (Jrv jrv : jrvs) {
            if (jrv.getJrvCitizenCollection().size() > 0) {
                if (!this.jrvCitizenModel.deleteJrvCitizenByJrv(jrv.getId())) {
                    isOk = false;
                    break;
                }
            }
        }

        if (isOk) {
            if (jrvModel.delete(actual, process.getId())) {
                if (creatJRV(auth, process, now)) {
                    Utilities.addMessageFlash("exito", "Se ha modificado la jrv");
                } else {
                    Utilities.addMessageFlash("error", "No se ha podido crear una nueva JRV");
                }
            } else {
                Utilities.addMessageFlash("error", "No se ha podido eliminar la anterior JRV");
            }
        } else {
            Utilities.addMessageFlash("error", "error al eliminar los participantes de la juntas");
        }
    }

    public void changeJRV() {
        Auth auth = Utilities.getUserAuth();

        if (auth != null) {
            int id = Integer.parseInt(Utilities.getRequestValue("frm:electoralProcessHeadId"));

            ElectoralProcess process = this.electoralProcessModel.getElectoralProcess(id);

            if (process != null) {
                int actual = Integer.parseInt(Utilities.getRequestValue("frm:headquarterActual"));
                int now = Integer.parseInt(Utilities.getRequestValue("frm:headquarterNow"));

                change(auth, process, actual, now);
                
            } else {
                Utilities.addMessageFlash("error", "No se ha podido obtener el proceso actual");
            }

        } else {
            Utilities.addMessageFlash("error", "No se ha podido obtener el usuario logeado");
        }
    }

    private void addPoliticGroupJrv(Jrv jrvId){
        for(PoliticGroups politic : this.politicGroupsModel.listPoliticGroups()){
            PoliticGroupVotes vote = new PoliticGroupVotes();
            vote.setJrvId(jrvId);
            vote.setPoliticGroupId(politic);
            vote.setElectoralProcessId(jrvId.getElectoralProcessId());
            vote.setVotes(new Integer(0));
            
            this.politicGroupVotesModel.insertPoliticGroupVotes(vote);
        }
    }
    
    public boolean creatJRV(Auth auth, ElectoralProcess process, int idHeadquarter) {
        if (auth.isGeneralAdministration()) {
            int min = 0;
            int max = 200;
            int add = 200;

            List<Citizens> list;
            boolean isOk = true;
            boolean isAdd = false;

            while ((list = this.citizenModel.listCitizenForJRV(idHeadquarter, min, max)).size() > 0) {
                isAdd = true;

                Jrv jrv = new Jrv();
                jrv.setCode("EP" + process.getCode() + "_" + Utilities.now().getYear() + auth.getCitizen().getId() + "_" + Utilities.createStringRandom(2));
                jrv.setElectoralProcessId(process);
                jrv.setHeadquarterId(this.headquartersModel.getHeadquarter(idHeadquarter));

                while (this.jrvModel.existByCode(jrv)) {
                    jrv.setCode("EP" + process.getCode() + "_" + Utilities.now().getYear() + auth.getCitizen().getId() + "_" + Utilities.createStringRandom(2));
                }

                if (this.jrvModel.insertJrv(jrv)) {
                    addPoliticGroupJrv(jrv);
                    
                    for (Citizens citizen : list) {
                        if (citizen.getCandidatesCollection().isEmpty()) {
                            JrvCitizen jrvCitizen = new JrvCitizen();
                            jrvCitizen.setJrvId(jrv);
                            jrvCitizen.setCitizenId(citizen);
                            jrvCitizen.setJrvCitizenTypeId(this.jrvCitizenTypesModel.getJrvCitizenTypes(4));

                            isOk = this.jrvCitizenModel.insertJrvCitizen(jrvCitizen);

                            if (!isOk) {
                                break;
                            }
                        }
                    }
                }

                if (!isOk) {
                    break;
                } else {
                    min += add;
                    max += add;
                }
            }

            if (!isAdd) {
                Jrv jrv = new Jrv();
                jrv.setCode("EP" + process.getCode() + "_" + Utilities.now().getYear() + auth.getCitizen().getId() + "_" + Utilities.createStringRandom(2));
                jrv.setElectoralProcessId(process);
                jrv.setHeadquarterId(this.headquartersModel.getHeadquarter(idHeadquarter));

                while (this.jrvModel.existByCode(jrv)) {
                    jrv.setCode("EP" + process.getCode() + "_" + Utilities.now().getYear() + auth.getCitizen().getId() + "_" + Utilities.createStringRandom(2));
                }

                if (this.jrvModel.insertJrv(jrv)){
                    addPoliticGroupJrv(jrv);
                    return true;
                }
            }

            return isOk;
        }

        return false;
    }

    public void save() {

        Auth auth = Utilities.getUserAuth();

        if (auth != null) {
            if (!this.electoralProcessModel.existsCode(electoralProcess)) {
                // Primero revisamos la diferencia entre las fechas
                if (Utilities.isEquealOrAfterNow(this.electoralProcess.getInitDate())) {
                    if (this.electoralProcess.getInitDate().before(this.electoralProcess.getEndDate())) { // En caso que EnDate sea nayor que InitDate
                        if (this.electoralProcess.getInitDate().before(this.electoralProcess.getProcessDate()) && this.electoralProcess.getEndDate().after(this.electoralProcess.getProcessDate())) { // En caso que el procees este entre el rango de fechas adecuados
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(this.electoralProcess.getProcessDate());
                            this.electoralProcess.setYear("" + cal.get(Calendar.YEAR));
                            this.electoralProcess.setElectoralProcessStatusId(electoralProcessStatusModel.getElectoralProcessStatus(1));
                            if (!this.electoralProcessModel.insertElectoralProcess(electoralProcess)) {
                                Utilities.addMessageError("Error_Insert", "No se ha podido registrar el proceso");
                            } else {
                                // Una vez insertado el bean
                                if (this.creatJRV(auth, electoralProcess, idHeadquarter)) {
                                    Utilities.addMessageFlash("exito", "se ha creado con exito el proceso electoral");
                                    Utilities.redirect("/faces/generalAdministration/processes.xhtml");
                                } else {
                                    Utilities.addMessageError("error", "No se ha podido crear la junta receptora de voto");
                                }
                            }
                        } else {
                            Utilities.addMessageError("Error_Fecha", "La fecha de proceso debe de estar entre los intervalos permitidos");
                        }
                    } else {
                        Utilities.addMessageError("Error_Fecha", "La fecha de finalizacion debe de ser posterior a la de inicializacion");
                    }
                } else {
                    Utilities.addMessageError("Error_Fecha", "La fecha de inicializacion debe de ser posterior a la fecha actual");
                }
            } else {
                Utilities.addMessageError("Error_Fecha", "El codigo ya existe");
            }
        } else {
            Utilities.addMessageError("Error_Fecha", "Error al obtener el usuario logeado");
        }
    }

    public List<ElectoralProcess> all() {
        return this.electoralProcessModel.listElectoralProcess();
    }

    public void onloadRequest() {
        this.electoralProcess = this.electoralProcessModel.getElectoralProcess(this.idRequest);
        this.avalibleActualProcess = this.electoralProcess.avalible();
        this.idStatus = this.electoralProcess.getElectoralProcessStatusId().getId();
    }

    public void update() {
        this.electoralProcess.setId(Integer.parseInt(Utilities.getRequestValue("frm:idRequest")));
        if (!this.electoralProcessModel.existsCodeWithId(electoralProcess)) {
            // Primero revisamos la diferencia entre las fechas
            if (Utilities.isEquealOrAfterNow(this.electoralProcess.getInitDate())) {
                if (this.electoralProcess.getInitDate().before(this.electoralProcess.getEndDate())) { // En caso que EnDate sea nayor que InitDate
                    if (this.electoralProcess.getInitDate().before(this.electoralProcess.getProcessDate()) && this.electoralProcess.getEndDate().after(this.electoralProcess.getProcessDate())) { // En caso que el procees este entre el rango de fechas adecuados
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(this.electoralProcess.getProcessDate());
                        this.electoralProcess.setYear("" + cal.get(Calendar.YEAR));
                        this.electoralProcess.setElectoralProcessStatusId(electoralProcessStatusModel.getElectoralProcessStatus(Integer.parseInt(Utilities.getRequestValue("frm:idStatus"))));
                        if (!this.electoralProcessModel.editElectoralProcess(electoralProcess)) {
                            Utilities.addMessageError("Error_Insert", "No se ha podido modificar el proceso");
                        } else {
                            Utilities.redirect("/faces/generalAdministration/processes.xhtml");
                        }
                    } else {
                        Utilities.addMessageError("Error_Fecha", "La fecha de proceso debe de estar entre los intervalos permitidos");
                    }
                } else {
                    Utilities.addMessageError("Error_Fecha", "La fecha de finalizacion debe de ser posterior a la de inicializacion");
                }
            } else {
                Utilities.addMessageError("Error_Fecha", "La fecha de inicializacion debe de ser posterior a la fecha actual");
            }
        } else {
            Utilities.addMessageError("Error_Fecha", "El codigo ya existe");
        }

    }

    public void delete() {
        ElectoralProcess electoral = this.electoralProcessModel.getElectoralProcess(this.electoralProcess.getId());

        if (electoral != null) {
            if (electoral.getCandidatesForCitiesCollection().size() > 0 || electoral.getPresidencialCandidatesCollection().size() > 0) {
                Utilities.addMessageFlash("error", "El proceso posee candidados relacionados");
            } else if (!this.electoralProcessModel.deleteElectoralProcess(electoral.getId())) {
                Utilities.addMessageFlash("error", "Error inesperado");
            }
        } else {
            Utilities.addMessageFlash("error", "No se ha podido eliminar el proceso");
        }
    }

    public void defineElectoralId(int id) {
        this.electoralProcess = this.electoralProcessModel.getElectoralProcess(id);
    }

    public int getIdHeadquarter() {
        return idHeadquarter;
    }

    public void setIdHeadquarter(int idHeadquarter) {
        this.idHeadquarter = idHeadquarter;
    }

    public int getIdHeadquarterActual() {
        return idHeadquarterActual;
    }

    public void setIdHeadquarterActual(int idHeadquarterActual) {
        this.idHeadquarterActual = idHeadquarterActual;
    }

    public void onIdHeadquarter() {
        if (this.electoralProcess.getJrvCollection() != null && this.electoralProcess.getJrvCollection().size() > 0) {

            List sedes;

            if (this.electoralProcess.getJrvCollection() instanceof List) {
                sedes = (List) this.electoralProcess.getJrvCollection();
            } else {
                sedes = new ArrayList(this.electoralProcess.getJrvCollection());
            }

            Jrv jrv = (Jrv) sedes.get(sedes.size() - 1);

            this.idHeadquarterActual = jrv.getHeadquarterId().getId();
        }
    }
}
