package com.claro.docsisweb.managed;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

import com.claro.docsisejb.bean.AuditRemote;
import com.claro.docsisejb.bean.CompilerRemote;
import com.claro.docsisejb.bean.GenerateFileRemote;
import com.claro.docsisejb.dto.DocSisDTO;
import com.claro.docsisejb.util.Constante;
import com.claro.docsisweb.util.Util;


@ManagedBean(
   name = "docSisManaged")
@SessionScoped
public class DocSisManaged {

   private static Logger LOGGER = LogManager.getLogger(DocSisManaged.class.getName());

   @EJB
   private GenerateFileRemote generateFileEJB;

   @EJB
   private CompilerRemote compilerEJB;

   @EJB
   private AuditRemote auditEJB;

   private String VPN_IDENTIFIER_DEFAULT = "Claro";

   private String URL_INICIO = "/DocSisWeb/faces/pages/docsis.xhtml";

   private String URL_CONFIGURACION = "/DocSisWeb/faces/config/configuracion.xhtml";

   private String userSession;

   private DocSisDTO docsis;

   private int minVLAN = 1;

   private int maxVLAN = 4096;

   private int minAncho = 1;

   private int maxAncho = 300;

   private String fileTotal;

   private String nameFileOut;

   private boolean userConfigManaged;
   
   private String expresionRegular;

   public String getUserSession() {
      return userSession;
   }

   public void setUserSession(String userSession) {
      this.userSession = userSession;
   }

   public DocSisDTO getDocsis() {
      return docsis;
   }

   public void setDocsis(DocSisDTO docsis) {
      this.docsis = docsis;
   }

   public int getMinVLAN() {
      return minVLAN;
   }

   public void setMinVLAN(int minimoVLAN) {
      this.minVLAN = minimoVLAN;
   }

   public int getMaxVLAN() {
      return maxVLAN;
   }

   public void setMaxVLAN(int maxVLAN) {
      this.maxVLAN = maxVLAN;
   }

   public int getMinAncho() {
      return minAncho;
   }

   public void setMinAncho(int minAncho) {
      this.minAncho = minAncho;
   }

   public int getMaxAncho() {
      return maxAncho;
   }

   public void setMaxAncho(int maxAncho) {
      this.maxAncho = maxAncho;
   }

   public String getNameFileOut() {
      return nameFileOut;
   }

   public void setNameFileOut(String nameFileOut) {
      this.nameFileOut = nameFileOut;
   }

   @PostConstruct
   public void inicializar() {
      validarSesion();
      cargarExpresionRegular();
      this.docsis = new DocSisDTO();
      this.docsis.setVPNIdentifier(VPN_IDENTIFIER_DEFAULT);
      
   }

   private void cargarExpresionRegular() {
      expresionRegular = generateFileEJB.cargarExpresionRegular();
   }

   public boolean validarSesion() {
      try {
         this.userSession = Util.getUserName();
         this.setUserConfigManaged(Util.getUserConfigManaged());
         return true;
      } catch (Exception e) {
         try {
            Util.redirecionar(Constante.URL_SALIR);
            inicializar();
            Util.addMessageInfo("Su Sesion ha cadudado");
            return false;
         } catch (Exception e1) {
            LOGGER.error("Error validando sesión", e1);
            return false;
         }
      }
   }

   public void inicializarLink() {
      cargarExpresionRegular();
      this.docsis = new DocSisDTO();
      this.docsis.setVPNIdentifier(VPN_IDENTIFIER_DEFAULT);
   }

   public boolean generarArchivo() {
      validarSesion();
      if ((nameFileOut = generateFileEJB.createFileDocSis(docsis)) != null) {
         Util.addMessageInfo(Util.DOCSIS, "El archivo " + nameFileOut + " ha sido creado exitosamente.");
         fileTotal = generateFileEJB.getFileTotal();
         return true;
      } else {
         Util.addMessageFatal("Ha ocurrido un error");
         LOGGER.error("Error validando sesión");
         return false;
      }

   }

   public boolean compilarArchivo() {
      validarSesion();
      try {
         compilerEJB.compilar(nameFileOut);
         Util.addMessageInfo(Util.DOCSIS, "El archivo ha sido compilado y enviado correctamente.");
         auditEJB.auditarGeneracionDocSis(docsis, Util.getUserName());

         return true;
      } catch (Exception e) {
         Util.addMessageFatal("Ha ocurrido un error");
         LOGGER.error("Error compilando archivo: " + e.getMessage());
         e.printStackTrace();

         return false;
      }

   }

   public boolean compilarInicio() {
      return generarArchivo() && compilarArchivo();
   }

   public String getFileTotal() {
      return fileTotal;
   }

   public void setFileTotal(String fileTotal) {
      this.fileTotal = fileTotal;
   }

   public void goInicio() {
      try {
         inicializarLink();
         Util.redirecionar(URL_INICIO);
      } catch (IOException e) {
         LOGGER.error("Ir a Inicio", e);
      }

   }

   public void goConfiguracion() {
      try {
         inicializarLink();
         Util.redirecionar(URL_CONFIGURACION);
      } catch (IOException e) {
         LOGGER.error("Ir a Configuracion", e);
      }

   }

   public void cerrarSesion() {
      try {
         Util.addMessageInfo("Sesión cerrada correctamente");
         Util.logout();
         Util.redirecionar(Constante.URL_SALIR);
      } catch (IOException e) {
         LOGGER.error("Cerrar Sesion", e);
      }
   }

   public void reset() {
      RequestContext.getCurrentInstance().reset("form:panel_docsis");
   }

   public boolean isUserConfigManaged() {
      return userConfigManaged;
   }

   public void setUserConfigManaged(boolean userConfigManaged) {
      this.userConfigManaged = userConfigManaged;
   }

   public String getExpresionRegular() {
      return expresionRegular;
   }

   public void setExpresionRegular(String expresionRegular) {
      this.expresionRegular = expresionRegular;
   }

}
