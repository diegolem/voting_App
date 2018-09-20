/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author pc
 */
public class Utilities {
    public static void addMessageError(String tag, String msg){
        FacesMessage fm = new FacesMessage(msg, tag);
        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }
    
    public static boolean isEquealOrAfterNow(Date date){
        LocalDate now = LocalDate.now();
        return (now.isBefore(Utilities.convertToLocalDateViaInstant(date))) || (now.isEqual(Utilities.convertToLocalDateViaInstant(date))); 
    }
    
    public static boolean isEquealOrBerofeNow(Date date){
        LocalDate now = LocalDate.now();
        return (now.isAfter(Utilities.convertToLocalDateViaInstant(date))) || (now.isEqual(Utilities.convertToLocalDateViaInstant(date))); 
    }
    
    public static void redirect(String url){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + url);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
