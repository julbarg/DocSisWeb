package com.claro.docsisejb.bean;

import javax.ejb.Remote;

import com.claro.docsisejb.dto.ConfigDTO;

@Remote
public interface ConfiguracionRemote {
   
   public ConfigDTO cargarConfiguracion();
   
   public boolean guardarConfiguracion(ConfigDTO config);

}
