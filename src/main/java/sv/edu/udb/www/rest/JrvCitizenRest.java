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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.JrvCitizenModel;

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/jrv_citizen")
public class JrvCitizenRest {

    @EJB
    private JrvCitizenModel jrvCitizenModel;
    @EJB
    private CitizenModel citizensModel;
    
    
    @GET
    @Path("/{dui}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<JrvCitizen> obtenerAdminJrv(@PathParam("dui") String dui){
        Citizens admin = citizensModel.getCitizen(dui);
        if(citizensModel.existsDui(admin)){
           if(citizensModel.isPresident(admin)){
               List list = new ArrayList(admin.getJrvCitizenCollection());
               return list;     
           }
        }
        return null;
    }
    
    @GET
    @Path("/{dui}/{password}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<JrvCitizen> loginAdminJrv(@PathParam("dui") String dui,@PathParam("password") String pass){
        Citizens admin = citizensModel.getLoginCitizenAdmin(dui,pass);
        if(citizensModel.isPresident(admin)){
            if(citizensModel.verifyCitizen(admin)){
               List list = new ArrayList(admin.getJrvCitizenCollection());
               return list;
            }        
        }
        return null;
    }
    
}
