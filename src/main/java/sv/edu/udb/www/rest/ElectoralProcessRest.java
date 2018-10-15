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
import javax.ws.rs.core.Response;
import sv.edu.udb.www.Entities.CitizenVotes;
import sv.edu.udb.www.Entities.Citizens;
import sv.edu.udb.www.Entities.ElectoralProcess;
import sv.edu.udb.www.Entities.JrvCitizen;
import sv.edu.udb.www.Entities.PoliticGroupVotes;
import sv.edu.udb.www.Entities.PoliticGroups;
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
                List<PoliticGroups> pre = null;
                //Filtrando los partidos que ya estan en el array
                for(PoliticGroups _pg : electoralProcessModel.getPoliticGroupPresidencial(electoral)){
                    if(!pre.contains(_pg)){
                        pre.add(_pg);
                    }
                }
                return Response.status(Response.Status.OK).entity(pre).build();
            } else {
                List<PoliticGroups> pre = null;
                //Filtrando los partidos que ya estan en el array
                for(PoliticGroups _pg : electoralProcessModel.getPoliticGroupCities(electoral)){
                    if(!pre.contains(_pg)){
                        pre.add(_pg);
                    }
                }
                return Response.status(Response.Status.OK).entity(pre).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/vote/{idAdmin}/{idGroup}/{dui}/{vote}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response sendVote(@PathParam("idAdmin") int idAdmin, @PathParam("idGroup") int politic, @PathParam("dui") String dui, @PathParam("vote") boolean vote) {
        Citizens citizen = citizenModel.getCitizen(dui);

        if (citizenVotesModel.verifyVote(citizen, idAdmin)) {
            JrvCitizen jrvAdmin = citizenVotesModel.getJrv(idAdmin);

            CitizenVotes citiVotes = new CitizenVotes();
            citiVotes.setCitizenId(citizen);
            citiVotes.setElectoralProcessId(jrvAdmin.getJrvId().getElectoralProcessId());
            citiVotes.setJrvId(jrvAdmin.getJrvId());

            if (vote) {
                citiVotes.setStatus((short) 1);
            }

            PoliticGroupVotes politicVotes = null;
            
            for (PoliticGroupVotes politicVote : jrvAdmin.getJrvId().getPoliticGroupVotesCollection()) {
                if (politicVote.getPoliticGroupId().getId() == politic) {
                    politicVotes = politicVote;
                    break;
                }
            }

            if (politicVotes != null && vote == true) {
                int vot = politicVotes.getVotes() + 1;
                politicVotes.setVotes(vot);
            }
            if (citizenVotesModel.editCitizenVotes(citiVotes)) {

                if (politicVotes != null) {
                    if (!politicGroupsVotesModel.editPoliticGroupVote(politicVotes)) {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(true).build();
                    }
                }

                return Response.status(Response.Status.OK).entity(true).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity(false).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(false).build();
        }
    }
}
