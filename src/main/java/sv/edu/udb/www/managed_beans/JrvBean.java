/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.naming.Context;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.CitizenTypes;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.JrvCitizenModel;
import sv.edu.udb.www.Model.JrvCitizenTypesModel;
import sv.edu.udb.www.Model.JrvModel;
import sv.edu.udb.www.Utilities;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.apache.commons.codec.digest.DigestUtils;
import sv.edu.udb.www.Entities.JrvCitizenTypes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Model.CitizenTypesModel;
import sv.edu.udb.www.Model.ElectoralProcessModel;
import sv.edu.udb.www.Model.ElectoralProcessStatusModel;
import sv.edu.udb.www.Validacion;

/**
 *
 * @author Diego Lemus
 */
@ManagedBean(name = "jrvBean")
@ViewScoped
public class JrvBean implements Serializable {

    @EJB
    private ElectoralProcessModel electoralProcessModel;
    @EJB
    private ElectoralProcessStatusModel electoralProcessStatusModel;
    @EJB
    private CitizenTypesModel citizenTypesModel;
    @EJB
    private JrvCitizenTypesModel jrvCitizenTypesModel;
    @EJB
    private JrvCitizenModel jrvCitizenModel;
    @EJB
    private CitizenModel citizenModel;
    @EJB
    private JrvModel jrvModel;

    private Jrv jrv;
    private Citizens citizen;
    private int idCitizen;
    private int idType;
    private JrvCitizen jrvCitizen;
    
    public Jrv getJrv() {
        return jrv;
    }

    public Citizens getCitizen() {
        return citizen;
    }

    public JrvCitizen getJrvCitizen() {
        return jrvCitizen;
    }

    public void setJrvCitizen(JrvCitizen jrvCitizen) {
        this.jrvCitizen = jrvCitizen;
    }

    

    public void setCitizen(Citizens citizen) {
        this.citizen = citizen;
    }

    public void setJrv(Jrv jrv) {
        this.jrv = jrv;
    }

    public int getIdCitizen() {
        return idCitizen;
    }

    public void setIdCitizen(int idCitizen) {
        this.idCitizen = idCitizen;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    /**
     * Creates a new instance of JrvBean
     */
    public JrvBean() {
        this.jrv = new Jrv();
    }
    
    public void loadCirinez(int idCitenez, int idJrv, int idJrvCitizen){
        this.citizen = this.citizenModel.getCitizen(idCitenez);
        this.jrv = this.jrvModel.getJrv(idJrv);
        this.jrvCitizen = this.jrvCitizenModel.getJrvCitizen(idJrvCitizen);
    }

    public void save() {

        if (!this.jrvModel.existByCode(jrv)) {
            if (this.jrvModel.insertJrv(jrv)) {
                Utilities.addMessageFlash("exito", "Se ha añadido una nueva Junta");
                Utilities.redirect("/faces/departmentalAdministration/jrv.xhtml");
            } else {
                Utilities.addMessageError("error", "No se ha podido regisrar la junta");
            }
        } else {
            Utilities.addMessageError("error", "Ya existe una JRV con la informacion especificada");
        }
    }
    
    public void savePresidencial() {

        if (!this.jrvModel.existByCode(jrv)) {
            if (this.jrvModel.insertJrv(jrv)) {
                Utilities.addMessageFlash("exito", "Se ha añadido una nueva Junta");
                Utilities.redirect("/faces/generalAdministration/jrv.xhtml");
            } else {
                Utilities.addMessageError("error", "No se ha podido regisrar la junta");
            }
        } else {
            Utilities.addMessageError("error", "Ya existe una JRV con la informacion especificada");
        }
    }

    public void update() {

        this.jrv.setCode(Utilities.getRequestValue("frm:codeEdit"));
        this.jrv.setId(Integer.parseInt(Utilities.getRequestValue("frm:idEdit")));
        this.jrv.getHeadquarterId().setId(Integer.parseInt(Utilities.getRequestValue("frm:headquarter")));
        this.jrv.getElectoralProcessId().setId(Integer.parseInt(Utilities.getRequestValue("frm:process")));

        if (!this.jrvModel.existByAnotherCode(jrv)) {
            if (this.jrvModel.editJrv(jrv)) {
                Utilities.addMessageFlash("exito", "Se ha actualizado la jrv");
            } else {
                Utilities.addMessageFlash("error", "No se ha podido axtualizar la junta");
            }
        } else {
            Utilities.addMessageFlash("error", "Ya existe una JRV con la informacion especificada");
        }
    }

    public void delete() {

        Jrv item = this.jrvModel.getJrv(Integer.parseInt(Utilities.getRequestValue("frm:idDelete")));

        if (item != null) {
            if (item.canDelete()) {
                if (this.jrvModel.deleteJrv(item.getId())) {
                    Utilities.addMessageFlash("exito", "Se ha eliminado la jrv");
                } else {
                    Utilities.addMessageFlash("error", "No se ha podido eliminar la junta");
                }
            } else {
                Utilities.addMessageFlash("error", "La junta posee datos relacionados");
            }
        } else {
            Utilities.addMessageFlash("error", "No se puede encontrar la junta receptora de votos");
        }
    }

    public List<Jrv> allJrv() {
        return this.jrvModel.listJrvs();
    }

    public void selectJrv(int id) {
        this.jrv = this.jrvModel.getJrv(id);
    }

    public void changeRolCitenez() {
        JrvCitizen jvrCitenez = this.jrvCitizenModel.getJrvCitizen(Integer.parseInt(Utilities.getRequestValue("frm:idJrvCitizenRol")));
        jvrCitenez.setCitizenId(this.citizenModel.getCitizen(Integer.parseInt(Utilities.getRequestValue("frm:idCitizenRol"))));
        jvrCitenez.setJrvCitizenTypeId(this.jrvCitizenTypesModel.getJrvCitizenTypes(Integer.parseInt(Utilities.getRequestValue("frm:idType"))));
        jvrCitenez.setJrvId(this.jrvModel.getJrv(Integer.parseInt(Utilities.getRequestValue("frm:idJvrRol"))));

        // revisamos si es el prisidente
        if (jvrCitenez.getJrvCitizenTypeId().getId() == 1){
            CitizenTypes type = this.citizenTypesModel.getCitizenTypes("PREJRV");
            jvrCitenez.getCitizenId().setCitizenTypeId(type);
            jvrCitenez.getCitizenId().setPassword(DigestUtils.sha256Hex(jvrCitenez.getCitizenId().getDui()));
            this.citizenModel.editCitizen(jvrCitenez.getCitizenId());
        }
        
        if (this.jrvCitizenModel.editJrvCitizen(jvrCitenez)) {
            Utilities.addMessageFlash("exito", "se ha modificado el rol un nuevo ciudadano");
        } else {
            Utilities.addMessageFlash("error", "No se ha podido añadir el nuevo ciudadano");
        }
    }

    public void removeCitenez() {
        String ids = Utilities.getRequestValue("frm:idsJvrCitinez");

        if (ids != null && !ids.isEmpty()) {

            for (String id : ids.split(",")) {
                if (id != null && !id.isEmpty()) {
                    JrvCitizen type = this.jrvCitizenModel.getJrvCitizen(Integer.parseInt(id));
                    Citizens citizen = type.getCitizenId();
                    
                    if (type != null) {
                        if (this.jrvCitizenModel.deleteJrvCitizen(type.getId())) {
                            if (citizen.getCitizenTypeId().getId().equals("PREJRV")){
                                citizen.setCitizenTypeId(this.citizenTypesModel.getCitizenTypes("CITIZN"));
                                this.citizenModel.editCitizen(citizen);
                            }
                            
                            Utilities.addMessageFlash("exito", "Se ha eliminado todos los miembros del jrv");
                        } else {
                            Utilities.addMessageFlash("error", "No se ha podido eliminar el miembro la junta");
                        }
                    } else {
                        Utilities.addMessageFlash("error", "No se ha podido eliminar todos los miembros la junta");
                    }
                }
            }

        } else {
            Utilities.addMessageFlash("error", "Debe de seleccionar miembros de la junta");
        }
    }

    public void generateReport() {
        int id = Integer.parseInt(Utilities.getRequestValue("frm:idJvrCitinez"));

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletResponse res = (HttpServletResponse) ec.getResponse();
        //Devuelve un objeto de la clase ServletOutputStream que modela un flujo de salida que permite la escritura de datos a nivel de bytes

        try {
            ServletOutputStream out = res.getOutputStream();

            //Obtenemos la conexion del pool de conexiones
            java.sql.Connection conexion = startConnection();

            if (conexion.isClosed()) {
                System.out.println("Error conexion cerrada");
            }

            //Nombre del archivo .jasper
            String url = ec.getRealPath("/WEB-INF/votingApp.jasper");

            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(ec.getRealPath("/WEB-INF/votingApp.jasper"));

            //Cargamos parametros del reporte (si tiene).
            Map parameters = new HashMap();
            parameters.put("id", id);
            //Enviamos la ruta del reporte los parametros y la conexion
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conexion);
            //Tipo de contenido a regresar al cliente
            res.setContentType("application/pdf");
            //Nombre del reporte
            res.setHeader("Content-disposition", "attachment; filename=jrv.pdf");
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection startConnection() {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "voting_app";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            System.out.println("Connected to the database");
        } catch (Exception e) {
            System.out.println("NO CONNECTION =(");
        }

        return conn;
    }
    
    public void startProcessElectoral(){
         int id = Integer.parseInt(Utilities.getRequestValue("frm:idJvr"));
         
         Jrv jrv = this.jrvModel.getJrv(id);
         
         if (jrv != null) {
            ElectoralProcess process = jrv.getElectoralProcessId();
            
            process.setElectoralProcessStatusId(this.electoralProcessStatusModel.getElectoralProcessStatus(3));
            this.electoralProcessModel.editElectoralProcess(process);
            
            Utilities.addMessageFlash("exito", "cambios realizados");
        } else
             Utilities.addMessageFlash("error", "No se ha podido localizar la junta");
    }
    
    public void endProcessElectoral(){
         int id = Integer.parseInt(Utilities.getRequestValue("frm:idJvr"));
         
         Jrv jrv = this.jrvModel.getJrv(id);
         
         if (jrv != null) {
            ElectoralProcess process = jrv.getElectoralProcessId();
            
            process.setElectoralProcessStatusId(this.electoralProcessStatusModel.getElectoralProcessStatus(4));
            this.electoralProcessModel.editElectoralProcess(process);
            
            Utilities.addMessageFlash("exito", "El proceso ha finalizado");
        } else
             Utilities.addMessageFlash("error", "No se ha podido localizar la junta");
    }
    
    public void verifyCitizens(){
        String dui = Utilities.getRequestValue("frm:dui");
        String me = Utilities.getRequestValue("frm:meDui");
        
        if (!me.equals(dui)) {
            if (Validacion.esDui(dui)) {
                Citizens citizen = this.citizenModel.getCitizen(dui);

                if (citizen != null) {

                    this.citizen = citizen;

                } else
                    Utilities.addMessageError("error", "No se ha encontrado ningun votante con dicho dui");
            } else
                Utilities.addMessageError("error", "El dui no es valido");
        } else
            Utilities.addMessageError("error", "Ha ingresado su mismo dui");
    }
}
