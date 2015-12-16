package com.claro.docsisejb.bean;

import java.rmi.RemoteException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import co.com.claro.sisges.ldap.service.LDAPAuthenticationServices;
import co.com.claro.sisges.ldap.service.LDAPAuthenticationServicesServiceLocator;

import com.claro.docsisejb.dao.UserConfigurationDAORemote;
import com.claro.docsisejb.dto.UsuarioDTO;


@Stateless
@LocalBean
public class AutenticacionEJB implements AutenticacionRemote {

   @EJB
   private UserConfigurationDAORemote userConfigurationDAO;

   private static final String DOMAIN_NAME = "co.attla.corp";

   public UsuarioDTO autenticar(UsuarioDTO usuario) throws RemoteException, ServiceException {

      // TODO Test
      if (true) {
         usuario.setConfigManaged(true);
         return usuario;
      }

      LDAPAuthenticationServicesServiceLocator ldapL = new LDAPAuthenticationServicesServiceLocator();
      LDAPAuthenticationServices query;
      query = ldapL.getLDAPAuthenticationServices();
      if (query.userAuthentication(usuario.getUserName(), usuario.getPassword(), DOMAIN_NAME)) {
         if (userConfigurationDAO.findByUser(usuario.getUserName()) != null) {
            usuario.setConfigManaged(true);
         }
         return usuario;
      } else {
         usuario.setConfigManaged(false);
         return null;
      }
   }

}
