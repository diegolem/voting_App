/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import static javax.servlet.SessionTrackingMode.URL;

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
        LocalDate target = convertToLocalDateViaInstant(date);
        return target.isAfter(now) || target.isEqual(now);
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
    
    public static String getPath(String folder){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        return context.getRealPath(folder).replace("\\target\\voting_App-1.0", "\\src\\main\\webapp");
    }
    
     public static int getYears(Date date) {
        try {
            
            Date now = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
            
            Calendar inicio = new GregorianCalendar();
            Calendar fin = new GregorianCalendar();
            
            inicio.setTime(date);
            fin.setTime(now);
            
            int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
            
            return difA;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
