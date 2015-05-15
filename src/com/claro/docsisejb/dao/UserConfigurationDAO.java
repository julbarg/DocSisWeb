package com.claro.docsisejb.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.claro.docsisejb.entity.UserConfigurationEntity;

@Stateless
@LocalBean
public class UserConfigurationDAO extends AbstractDAO<UserConfigurationEntity> implements UserConfigurationDAORemote{
   
   private static final String USER = "user";
   
   @Override
   public UserConfigurationEntity findByUser(String user) {
      Query query = entityManager.createNamedQuery("UserConfigurationEntity.findByUser",
         UserConfigurationEntity.class);
      query.setParameter(USER, user);
      try{
         return (UserConfigurationEntity) query.getSingleResult();
      }catch (NoResultException e){
         return null;
      }
      
      
   }
   

}
