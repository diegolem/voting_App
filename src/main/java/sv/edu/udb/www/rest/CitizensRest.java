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

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/citizens")
public class CitizensRest {

    @EJB
    private CitizenModel citizenModel;

    @GET
    @Path("/{dui}")
    @Produces({MediaType.APPLICATION_JSON})
    public Citizens obtenerInformacionCitizens(@PathParam("dui") String dui) {
        Citizens citizen = citizenModel.getCitizen(dui);
        if (citizenModel.existsDui(citizen)) {
            return citizen;
        }
        return null;
    }
}
