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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sv.edu.udb.www.managed_beans.Auth;

/**
 *
 * @author pc
 */
public class Utilities {

    public static void addMessageError(String tag, String msg) {
        FacesMessage fm = new FacesMessage(msg, tag);
        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public static String getParam(String name) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter(name);
    }

    public static void AddMessage(String name, String message) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put(name, message);
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static boolean validateMayorEdad(Date fechaC) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate now = LocalDate.now();

        return now.compareTo(convertToLocalDateViaInstant(fechaC)) >= 18 && now.compareTo(convertToLocalDateViaInstant(fechaC)) <= 120;
    }

    public static boolean isEquealOrAfterNow(Date date) {
        LocalDate now = LocalDate.now();
        LocalDate target = convertToLocalDateViaInstant(date);
        return target.isAfter(now) || target.isEqual(now);
    }

    public static boolean isEquealOrBerofeNow(Date date) {
        LocalDate now = LocalDate.now();
        return (now.isAfter(Utilities.convertToLocalDateViaInstant(date))) || (now.isEqual(Utilities.convertToLocalDateViaInstant(date)));
    }

    public static void redirect(String url) {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + url);
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getPath(String folder) {
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }

    public static void addMessageFlash(String name, String message) {
        Flash messages = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        messages.put(name, message);
    }

    public static String getRequestValue(String name) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    public static Auth getUserAuth() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest req = (HttpServletRequest) ec.getRequest();

        HttpSession session = req.getSession(false);

        if (session != null) {
            return (Auth) session.getAttribute("Auth");// Obtenemos la variable de session
        } else {
            return null;
        }
    }

    public static LocalDate now() {
        return LocalDate.now();
    }

    public static String createStringRandom(int lenght) {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(lenght);

        for (int i = 0; i < lenght; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        return buffer.toString();
    }

    public static Date convertStringToDate(String date) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            return format.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
}
