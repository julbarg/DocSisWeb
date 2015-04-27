package com.claro.docsisweb.managed;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.claro.docsisejb.bean.ConfiguracionRemote;
import com.claro.docsisejb.dto.ConfigDTO;
import com.claro.docsisejb.util.Constante;
import com.claro.docsisweb.util.Util;


@ManagedBean(
   name = "configManaged")
@RequestScoped
public class ConfigManaged {

   private ConfigDTO config;

   private String userSession;

   @EJB
   private ConfiguracionRemote configuracionEJB;

   @PostConstruct
   public void inicializar() {
      if (validarSesion()) {
         config = configuracionEJB.cargarConfiguracion();
      }
   }

   public boolean guardarConfiguracion() {
      if (validarSesion() && configuracionEJB.guardarConfiguracion(config)) {
         Util.addMessageInfo(Util.CONFIG, "El archivo de configuración ha sido modificado exitosamente");
         return true;
      }
      return false;
   }

   public boolean validarSesion() {
      try {
         this.setUserSession(Util.getUserName());
         if(!Util.getUserConfigManaged()){
          throw new Exception();  
         }
         return true;
      } catch (Exception e) {
         try {
            Util.redirecionar(Constante.URL_SALIR);
            Util.addMessageInfo("Su Sesion ha cadudado");
            return false;
         } catch (Exception e1) {
            return false;
         }
      }
   }

   public ConfigDTO getConfig() {
      return config;
   }

   public void setConfig(ConfigDTO config) {
      this.config = config;
   }

   public String getUserSession() {
      return userSession;
   }

   public void setUserSession(String userSession) {
      this.userSession = userSession;
   }

}
