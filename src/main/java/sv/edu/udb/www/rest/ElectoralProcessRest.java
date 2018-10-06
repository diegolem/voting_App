/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import sv.edu.udb.www.Model.ElectoralProcessModel;

/**
 *
 * @author Diego Lemus
 */
@Stateless
@Path("/electoralProcess")
public class ElectoralProcessRest {

    @EJB
    private ElectoralProcessModel electoralProcessModel;
    
    
    
}
