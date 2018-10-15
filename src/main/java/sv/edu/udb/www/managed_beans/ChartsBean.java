/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
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
@RequestScoped
public class ChartsBean {

    @EJB
    private ElectoralProcessModel electoralProcessModel;

    @EJB
    private JrvModel jrvModel;

    @EJB
    private DepartmentsModel departmentsModel;

    @EJB
    private CitiesModel citiesModel;
    
    @EJB
    private HeadquartersModel headquarterModel;
       
    private Cities citi;
    
    private Departments department;
    
    private Jrv jrv;
    
    private Headquarters headquarter;
    
    private int idProcess;
    
    private PieChartModel chartDepartment;

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

    public int getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(int idProcess) {
        this.idProcess = idProcess;
    }
    
    public void graficaDepartamentos(){
//        chartDepartment = new PieChartModel();
        ElectoralProcess _ep = electoralProcessModel.getElectoralProcess(6);
        List<CitizenVotes> listaVotantes = departmentsModel.listDepartmentCount(_ep);
        List<Integer> listaId = null;
        List<Integer> cuentaV = null;
        int cuenta = 0;
        if(!listaVotantes.isEmpty()){
            //List de los ID de los departamentos
            for(CitizenVotes _cv : listaVotantes){
                int id = _cv.getCitizenId().getHeadquarterId().getCityId().getDeparmentId().getId();
                if(!listaId.contains(id)){
                    listaId.add(id);
                }
            }
            //List de la cuenta de los votos
            for(CitizenVotes _cv : listaVotantes){
                int position = listaId.get(_cv.getCitizenId().getHeadquarterId().getCityId().getDeparmentId().getId());
                if(cuentaV.get(position) > 0){
                   //si el index en la position del id es diferente del id se suma
                   cuentaV.get(position + 1);
                }else{
                    //si no se añade la position
                    cuentaV.add(position, 0);
                    cuentaV.get(position + 1);
                }
                //chartDepartment.set("a", 1);
            }
            for(Integer _i : cuentaV){
                System.out.println(_i + "CUENTA " + (cuenta + 1));
            }
//        chartDepartment.setTitle("Estadisticas por departamentos");
//        chartDepartment.setLegendPosition("w");
//        chartDepartment.setShadow(false);
        }
    }
    public ChartsBean() {
    }
    
    public List<ElectoralProcess> getElectoralProcess(){
        return electoralProcessModel.listElectoralProcess();
    }
    
}
