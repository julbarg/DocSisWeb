package com.claro.docsisejb.dto;

import java.io.Serializable;


public class ConfigDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = -8705231309903057228L;

   private String pathConfig;

   private String pathData;

   private String serverFTP;

   private String portFTP;

   private String userNameFTP;

   private String passwordFTP;

   private String workingDirFTP;

   private String expresionRegularMAC;

   public ConfigDTO() {
   }

   public String getPathConfig() {
      return pathConfig;
   }

   public void setPathConfig(String pathConfig) {
      this.pathConfig = pathConfig;
   }

   public String getPathData() {
      return pathData;
   }

   public void setPathData(String pathData) {
      this.pathData = pathData;
   }

   public String getServerFTP() {
      return serverFTP;
   }

   public void setServerFTP(String serverFTP) {
      this.serverFTP = serverFTP;
   }

   public String getPortFTP() {
      return portFTP;
   }

   public void setPortFTP(String portFTP) {
      this.portFTP = portFTP;
   }

   public String getUserNameFTP() {
      return userNameFTP;
   }

   public void setUserNameFTP(String userNameFTP) {
      this.userNameFTP = userNameFTP;
   }

   public String getPasswordFTP() {
      return passwordFTP;
   }

   public void setPasswordFTP(String passwordFTP) {
      this.passwordFTP = passwordFTP;
   }

   public String getWorkingDirFTP() {
      return workingDirFTP;
   }

   public void setWorkingDirFTP(String workingDirFTP) {
      this.workingDirFTP = workingDirFTP;
   }

   public String getExpresionRegularMAC() {
      return expresionRegularMAC;
   }

   public void setExpresionRegularMAC(String expresionRegularMAC) {
      this.expresionRegularMAC = expresionRegularMAC;
   }
}
