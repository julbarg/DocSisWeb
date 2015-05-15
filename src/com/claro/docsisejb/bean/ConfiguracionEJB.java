package com.claro.docsisejb.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.claro.docsisejb.dto.ConfigDTO;
import com.claro.docsisejb.dto.FTPDTO;
import com.claro.docsisejb.util.Constante;
import com.claro.docsisejb.util.Util;


@Stateless
@LocalBean
public class ConfiguracionEJB implements ConfiguracionRemote {
   
   public ConfigDTO cargarConfiguracion() {
      ConfigDTO configDTO = new ConfigDTO();
      configDTO.setPathConfig(Util.readProperties(Util.PATH_DOCSIS_CONFIG));
      configDTO.setPathData(Util.readProperties(Util.PATH_DOCSIS_DATA));
      configDTO.setConfigConsola(Util.readProperties(Constante.CMD));
      
      configDTO.setServerFTP(Util.readProperties(FTPDTO.SERVER_FTP));
      configDTO.setPortFTP(Util.readProperties(FTPDTO.PORT_FTP));
      configDTO.setUserNameFTP(Util.readProperties(FTPDTO.USER_NAME_FTP));
      configDTO.setPasswordFTP(Util.readProperties(FTPDTO.PASSWOR_FTP));      
      configDTO.setWorkingDirFTP(Util.readProperties(FTPDTO.FTP_WORKING_DIR));
      
      configDTO.setExpresionRegularMAC(Util.readProperties(FTPDTO.EXPRESION_REGULAR));

      return configDTO;
   }

   public boolean guardarConfiguracion(ConfigDTO config) {
      Util.writeProperties(Util.PATH_DOCSIS_CONFIG, config.getPathConfig());
      Util.writeProperties(Util.PATH_DOCSIS_DATA, config.getPathData());
      Util.writeProperties(Constante.CMD, config.getConfigConsola());
      
      Util.writeProperties(FTPDTO.SERVER_FTP, config.getServerFTP());
      Util.writeProperties(FTPDTO.PORT_FTP, config.getPortFTP());
      Util.writeProperties(FTPDTO.USER_NAME_FTP, config.getUserNameFTP());
      Util.writeProperties(FTPDTO.PASSWOR_FTP, config.getPasswordFTP());
      Util.writeProperties(FTPDTO.FTP_WORKING_DIR, config.getWorkingDirFTP());
      
      Util.writeProperties(FTPDTO.EXPRESION_REGULAR, config.getExpresionRegularMAC());
      
      return true;
   }
}
