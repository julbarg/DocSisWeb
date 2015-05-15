package com.claro.docsisejb.dto;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -5960756666333312175L;

   private String userName;

   private String password;
   
   private boolean configManaged;

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isConfigManaged() {
      return configManaged;
   }

   public void setConfigManaged(boolean configManaged) {
      this.configManaged = configManaged;
   }

}
