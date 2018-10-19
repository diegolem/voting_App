/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.Headquarters;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.HeadquartersModel;

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/headquarters")
public class HeadquartersRest {

    @EJB
    private HeadquartersModel headquartersModel;
    @EJB
    private CitizenModel citizensModel;
    
    @GET
    @Path("/{dui}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getHeadquart(@PathParam("dui") String dui){
        Citizens cit = citizensModel.getCitizen(dui);
        if(citizensModel.existsDui(cit)){
            return Response.ok(headquartersModel.listHeadForDui(cit).get(0)).build();
        }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
