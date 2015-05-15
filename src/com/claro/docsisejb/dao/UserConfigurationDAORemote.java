package com.claro.docsisejb.dao;

import javax.ejb.Remote;

import com.claro.docsisejb.entity.UserConfigurationEntity;

@Remote
public interface UserConfigurationDAORemote {

   public UserConfigurationEntity findByUser(String user);

}
