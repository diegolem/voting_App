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
import sv.edu.udb.www.Model.CitiesModel;
import sv.edu.udb.www.Model.DepartmentsModel;
import sv.edu.udb.www.Model.ElectoralProcessModel;
import sv.edu.udb.www.Model.HeadquartersModel;
import sv.edu.udb.www.Model.JrvModel;

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
    
    @EJB
    private CitiesModel citiesModel;
    
    @EJB
    private HeadquartersModel headquartersModel;
    
    @EJB
    private JrvModel jrvModel;
    
    @ManagedProperty("#{param.id}")
    private int idRequest;

    public int getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }
    
    private int idD;
    
    private int idC;
    
    private int idJ;
    
    private int idS;
    
    private Cities citi;
    
    private Departments department;
    
    private Jrv jrv;
    
    private Headquarters headquarter;
        
    private PieChartModel pieD;
    
    private PieChartModel pieC;
    
    private PieChartModel pieJ;
    
    private PieChartModel pieS;

    public PieChartModel getPieD() {
        return pieD;
    }

    public int getIdD() {
        return idD;
    }

    public void setIdD(int idD) {
        this.idD = idD;
    }
    public int getIdJ() {
        return idJ;
    }
    
    public void setIdS(int idS) {
        this.idS = idS;
    }
    public int getIdS() {
        return idS;
    }
    
    public void setIdJ(int idJ) {
        this.idJ = idJ;
    }
    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public PieChartModel getPieC() {
        return pieC;
    }

    public PieChartModel getPieJ() {
        return pieJ;
    }

    public PieChartModel getPieS() {
        return pieS;
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
        pieD.setTitle("Votos por departamento");
        pieD.setLegendPosition("w");
        pieD.setShadow(false);
    }
    
    public void graficaMunicipios(){
        this.pieC = new PieChartModel();
        if(this.idD == 0){
            this.idD = 1;
        }
        
        ElectoralProcess _ep = electoralProcessModel.getElectoralProcess(this.idRequest);
        List <Integer> listaVotos = new ArrayList();
//        Departments _de = departmentsModel.getDepartment(this.idD);
        for(Cities _c : citiesModel.listCitiesforDepartment(this.idD)){
            List<CitizenVotes> listaVotantes = new ArrayList();
            listaVotantes = departmentsModel.listCitiesForDepartmentsCount(_ep, _c.getId());
            
            listaVotos.add(listaVotantes.size());
        }
        
        List<Cities> listaC = citiesModel.listCitiesforDepartment(this.idD);
        for(int i = 0; i < listaVotos.size(); i++){
            pieC.set(listaC.get(i).getName(), listaVotos.get(i));
        }
        
        pieC.setTitle("Votos por Municipios");
        pieC.setLegendPosition("w");
        pieC.setShadow(false);
    }

    public void graficaSedes(){
        this.pieS = new PieChartModel();
        if(this.idC == 0){
            this.idC = 2;
        }
        ElectoralProcess _ep = electoralProcessModel.getElectoralProcess(this.idRequest);
        List <Integer> listaVotos = new ArrayList();
//        Cities _ci = citiesModel.getCities(idC);
        for(Headquarters _h : headquartersModel.listHeadquartersForCity(this.idC)){
            List<CitizenVotes> listaVotantes = new ArrayList();    
            listaVotantes = departmentsModel.listHeadquartersForCitiesCount(_ep, _h.getId());
            listaVotos.add(listaVotantes.size());
        }
        List <Headquarters> listaH = headquartersModel.listHeadquartersForCity(this.idC);
        for(int i = 0; i < listaVotos.size(); i++){
            pieS.set(listaH.get(i).getName(), listaVotos.get(i));
        }
        pieS.setTitle("Votos por Sedes");
        pieS.setLegendPosition("w");
        pieS.setShadow(false);
    }
    
    public void graficaJrv(){
        
    }
    public int votosTotales(){
        ElectoralProcess _ep = electoralProcessModel.getElectoralProcess(this.idRequest);
        return departmentsModel.totalCount(_ep).size();
    }
    public ChartsBean(){
        this.citi = new Cities();
        this.department = new Departments();
        this.headquarter = new Headquarters();
        this.jrv = new Jrv();
    }
    
    public List<ElectoralProcess> getElectoralProcess(){
        return electoralProcessModel.listElectoralProcess();
    }
    
    public List<Departments> getDepartmentsC(){
        return departmentsModel.getDepartments();
    }
    public List<Headquarters> getHeadquarterC(){
        return headquartersModel.listHeadquarters();
    }
    public List<Cities> getCitiesC(){
        return citiesModel.listCities();
    }
    public List<Jrv> getJrvC(){
        return jrvModel.listJrvByProcess(this.idRequest);
    }
    
    private void createChart() {
        graficaDepartamentos();
        graficaMunicipios();
        graficaSedes();
        graficaJrv();
    }
    
}
