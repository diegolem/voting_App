/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.PieChartModel;
import sv.edu.udb.www.Entities.Cities;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Departments;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.ElectoralProcessModel;

/**
 *
 * @author Diego Lemus
 */

@ManagedBean(name = "chartsBean")
@ViewScoped
public class ChartsBean implements Serializable {

    @EJB
    private ElectoralProcessModel electoralProcessModel;

    @EJB
    private DepartmentsModel departmentsModel;
    
    @ManagedProperty("#{param.id}")
    private int idRequest;

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }
       
    private Cities citi;
    
    private Departments department;
    
    private Jrv jrv;
    
    private Headquarters headquarter;
        
    private PieChartModel pieD;

    public PieChartModel getPieD() {
        return pieD;
    }

    public Cities getCiti() {
        return citi;
    }

    public void setCiti(Cities citi) {
        this.citi = citi;
    }

    public Departments getDepartment() {
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public Jrv getJrv() {
        return jrv;
    }

    public void setJrv(Jrv jrv) {
        this.jrv = jrv;
    }

    public Headquarters getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(Headquarters headquarter) {
        this.headquarter = headquarter;
    }
    
    @PostConstruct
    public void init() {
        createChart();
    }
    
    public void graficaDepartamentos(){
        pieD = new PieChartModel();
        if(this.idRequest == 0){
            this.idRequest = 1;
        }
        ElectoralProcess _ep = electoralProcessModel.getElectoralProcess(this.idRequest);
        List <Integer> listaVotos = new ArrayList();
        for(Departments _d : departmentsModel.getDepartments()){
            List<CitizenVotes> listaVotantes = new ArrayList();
            listaVotantes = departmentsModel.listDepartmentCount(_ep, _d.getId());
            
            listaVotos.add(listaVotantes.size());
        }

        List <Departments> listaD = departmentsModel.getDepartments();
        for(int i = 0; i < listaVotos.size(); i++){
            pieD.set(listaD.get(i).getName(), listaVotos.get(i));
        }
        //
        //

        pieD.setTitle("Votos por departamento");
        pieD.setLegendPosition("w");
        pieD.setShadow(false);
    }
    
    public ChartsBean() {
        this.citi = new Cities();
        this.department = new Departments();
        this.headquarter = new Headquarters();
        this.jrv = new Jrv();
    }
    
    public List<ElectoralProcess> getElectoralProcess(){
        return electoralProcessModel.listElectoralProcess();
    }

    private void createChart() {
        graficaDepartamentos();
    }
    
}
