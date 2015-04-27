package com.claro.docsisweb.util;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {
   
   public static final String USER_NAME = "userName";
   
   public static final String USER_ID = "userID";

   public static final String USER_CONFIG_MANAGED = "userConfigManaged";

   public static final String DOCSIS = "docsis";

   public static final String CONFIG = "config";

   public static HttpSession getSession() {
      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
   }

   public static HttpServletRequest getRequest() {
      return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
   }

   public static String getUserName() throws Exception{
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      return session.getAttribute(Util.USER_NAME).toString();
   }

   public static boolean getUserConfigManaged() {
      HttpSession session = getSession();
      return (boolean) session.getAttribute(Util.USER_CONFIG_MANAGED);   
   }
   
   public static String getUserId() {
      HttpSession session = getSession();
      if (session != null)
         return (String) session.getAttribute(Util.USER_ID);
      else
         return null;
   }
   
   public static void logout() {
      HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
      session.invalidate();
   }
   
   public static void redirecionar(String url) throws IOException{
      FacesContext.getCurrentInstance().getExternalContext().redirect(url);
   }
   
   public static void addMessageInfo(String mensaje){
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
      FacesContext.getCurrentInstance().addMessage(null, message);
   }
   
   public static void addMessageInfo(String component, String mensaje){
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, null);
      FacesContext.getCurrentInstance().addMessage(component, message);
   }
   
   public static void addMessageFatal(String mensaje){
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, mensaje, null);
      FacesContext.getCurrentInstance().addMessage(null, message);
      
   }
}