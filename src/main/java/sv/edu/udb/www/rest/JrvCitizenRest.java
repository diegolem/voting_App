/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Jrv;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.JrvModel;

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/jrv_citizen")
public class JrvCitizenRest {

    @EJB
    private CitizenModel citizensModel;
    @EJB
    private JrvModel jrvModel;

    @GET
    @Path("/{dui}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<JrvCitizen> obtenerAdminJrv(@PathParam("dui") String dui) {
        Citizens admin = citizensModel.getCitizen(dui);
        if (citizensModel.existsDui(admin)) {
            if (citizensModel.isPresident(admin)) {
                List list = new ArrayList(admin.getJrvCitizenCollection());
                return list;
            }
        }
        return null;
    }
    @GET
    @Path("/jrv/{idjrv}/citizens")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Citizens> obtenerCitizensForJrv(@PathParam("idjrv") int id){
        Jrv jrvCitizens = jrvModel.getJrv(id);
        if(jrvCitizens != null){
            if(citizensModel.verificarProcesosActivosJrv(jrvCitizens)){
                List list = new ArrayList(jrvCitizens.getHeadquarterId().getCitizensCollection());
                return list;            
            }
        }
        return null;
    }
    
    @GET
    @Path("/{dui}/{password}")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public Response loginAdminJrv(@PathParam("dui") String dui, @PathParam("password") String pass) {
        Citizens admin = citizensModel.getLoginCitizenAdmin(dui, pass);
        if (citizensModel.isPresident(admin)) {
            if (citizensModel.verifyCitizen(admin)) {
                if (citizensModel.verificarProcesosActivos(admin)) {
                    List list = new ArrayList(admin.getJrvCitizenCollection());
                    return Response.status(Response.Status.OK).entity(list.get(0)).build();
                }
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
