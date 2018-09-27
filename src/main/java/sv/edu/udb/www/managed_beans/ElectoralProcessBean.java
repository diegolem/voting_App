/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
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
/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "electoralProcessBean")
@ViewScoped
public class ElectoralProcessBean implements Serializable {

    @EJB
    private ElectoralProcessStatusModel electoralProcessStatusModel;

    @EJB
    private ElectoralProcessModel electoralProcessModel;

    private ElectoralProcess electoralProcess;

    @ManagedProperty("#{param.id}")
    private int idRequest;

    private int idStatus;

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

    public List<ElectoralProcess> allElectoralProcessByEndDate(){
        return this.electoralProcessModel.listElectoralProcessByEndDate();
    }
    
    public void save() {
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

    public List<ElectoralProcess> all() {
        return this.electoralProcessModel.listElectoralProcess();
    }

    public void onloadRequest() {
        this.electoralProcess = this.electoralProcessModel.getElectoralProcess(this.idRequest);
        this.idStatus = this.electoralProcess.getElectoralProcessStatusId().getId();
    }

    public void update(int status, int id) {
        this.electoralProcess.setId(id);
        if (!this.electoralProcessModel.existsCodeWithId(electoralProcess)) {
            // Primero revisamos la diferencia entre las fechas
            if (Utilities.isEquealOrAfterNow(this.electoralProcess.getInitDate())) {
                if (this.electoralProcess.getInitDate().before(this.electoralProcess.getEndDate())) { // En caso que EnDate sea nayor que InitDate
                    if (this.electoralProcess.getInitDate().before(this.electoralProcess.getProcessDate()) && this.electoralProcess.getEndDate().after(this.electoralProcess.getProcessDate())) { // En caso que el procees este entre el rango de fechas adecuados
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(this.electoralProcess.getProcessDate());
                        this.electoralProcess.setYear("" + cal.get(Calendar.YEAR));
                        this.electoralProcess.setElectoralProcessStatusId(electoralProcessStatusModel.getElectoralProcessStatus(status));
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
    
     public void delete(int id) {
        ElectoralProcess electoral = this.electoralProcessModel.getElectoralProcess(id);
        
        if (electoral != null & electoral.avalible()) {
             this.electoralProcessModel.deleteElectoralProcess(id);
        }
    }
}
