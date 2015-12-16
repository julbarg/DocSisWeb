package com.claro.docsisweb.managed;

import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ServiceException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.claro.docsisejb.bean.AutenticacionRemote;
import com.claro.docsisejb.dto.UsuarioDTO;
import com.claro.docsisweb.util.Util;

@ManagedBean(name = "loginManaged")
@SessionScoped
public class LoginManaged {
   
   private static Logger LOGGER = LogManager.getLogger(LoginManaged.class.getName());
   
   @EJB
   private AutenticacionRemote autenticacionEJB;
   
   private UsuarioDTO usuario;

   public UsuarioDTO getUsuario() {
      return usuario;
   }

   public void setUsuario(UsuarioDTO usuario) {
      this.usuario = usuario;
   }
   
   public boolean autenticar(){
      try {
         if((usuario = autenticacionEJB.autenticar(usuario)) != null){
            final HttpSession session = Util.getSession();
            session.setAttribute(Util.USER_NAME, this.usuario.getUserName());
            session.setAttribute(Util.USER_CONFIG_MANAGED,this.usuario.isConfigManaged());
            return true;
         }else{
            Util.addMessageFatal("Usuario y/o Contraseña Incorrecta");
            return false;
         }
         
      } catch (RemoteException | ServiceException e) {
         Util.addMessageFatal("Usuario y/o Contraseña Incorrecta");
         LOGGER.error("Autenticacion", e);
         return false;         
      } catch (Exception e) {
         Util.addMessageFatal("Error");
         LOGGER.error("Autenticacion", e);
         return false;
      }
      
      
   }
   
   @PostConstruct
   private void inicializar(){
      usuario = new UsuarioDTO();
   }

}
