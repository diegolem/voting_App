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
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.PoliticGroupVotes;
import sv.edu.udb.www.Entities.PoliticGroups;
import sv.edu.udb.www.Entities.PresidencialCandidates;
import sv.edu.udb.www.Model.CitizenModel;
import sv.edu.udb.www.Model.CitizenVotesModel;
import sv.edu.udb.www.Model.ElectoralProcessModel;
import sv.edu.udb.www.Model.PoliticGroupVotesModel;

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
    @EJB
    private PoliticGroupVotesModel politicGroupsVotesModel;

    @GET
    @Path("/{code}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPoliticGroupsForElectoraProcess(@PathParam("code") String codigo) {
        ElectoralProcess electoral = electoralProcessModel.getElectoralProcess(codigo);
        if (electoralProcessModel.existsCode(electoral)) {
            if (electoral.getElectoralProcessTypesId().getId() == 1) {
                List<PoliticGroups> pre = electoralProcessModel.getPoliticGroupPresidencial(electoral);
                return Response.status(Response.Status.OK).entity(pre).build();
            } else {
                List<PoliticGroups> pre = electoralProcessModel.getPoliticGroupCities(electoral);
                return Response.status(Response.Status.OK).entity(pre).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    public String sendVote(@FormParam("idPolitic") int politic, @FormParam("dui") String dui, @FormParam("vote") boolean vote, @FormParam("idadmin") int idAdmin) {
        Citizens citizen = citizenModel.getCitizen(dui);
        if (citizenVotesModel.verifyVote(citizen, idAdmin)) {
            Citizens _citizen = citizenModel.getCitizen(dui);
            JrvCitizen jrvAdmin = citizenVotesModel.getJrv(idAdmin);

            CitizenVotes citiVotes = new CitizenVotes();
            citiVotes.setCitizenId(_citizen);
            citiVotes.setElectoralProcessId(jrvAdmin.getJrvId().getElectoralProcessId());
            citiVotes.setJrvId(jrvAdmin.getJrvId());

            citiVotes.setStatus((short) (vote ? (short) 1 : 0));

            PoliticGroupVotes politicVotes = null;

            for (PoliticGroupVotes politicVote : jrvAdmin.getJrvId().getPoliticGroupVotesCollection()) {
                if (politicVote.getPoliticGroupId().getId() == politic) {
                    politicVotes = politicVote;
                    break;
                }
            }

            //citizenVotesModel.countVote(politic, jrvAdmin.getJrvId().getId(), jrvAdmin.getJrvId().getElectoralProcessId().getId());
            if (politicVotes != null && vote == true) {
                int vot = politicVotes.getVotes() + 1;
                politicVotes.setVotes(vot);
            }

            if (citizenVotesModel.insertCitizenVotes(citiVotes)) {
                if (politicGroupsVotesModel.editPoliticGroupVote(politicVotes)) {
                    return "Bien";

                } else {
                    return "partido";
                }
            } else {
                return "ciudadano";
            }
        } else {
            return "ya votó";
        }
    }
}
