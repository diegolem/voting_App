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
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenVotesModel;
import sv.edu.udb.www.Model.ElectoralProcessModel;

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/electoral")
public class ElectoralProcessRest {

    @EJB
    private ElectoralProcessModel electoralProcessModel;
    @EJB
    private CitizenVotesModel citizenVotesModel;
    @EJB
    private CitizenModel citizenModel;

    @GET
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<PoliticGroups> getPoliticGroupsForElectoraProcess(@PathParam("code") String codigo) {
        ElectoralProcess electoral = electoralProcessModel.getElectoralProcess(codigo);
        if (electoralProcessModel.existsCode(electoral)) {
            if (electoral.getElectoralProcessTypesId().getId() == 1) {
                return electoralProcessModel.getPoliticGroupPresidencial(electoral);
            } else {
                return electoralProcessModel.getPoliticGroupCities(electoral);
            }
        }
        return null;
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public String sendVote(@FormParam("idPolitic") int politic, @FormParam("dui") String dui, @FormParam("vote") String vote, @FormParam("idadmin") int idAdmin) {
        Citizens citizen = citizenModel.getCitizen(dui);
        if (citizenVotesModel.verifyVote(citizen, idAdmin)) {
            
        }
        return null;
    }
}
