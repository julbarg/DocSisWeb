package com.claro.docsisejb.entity;

import java.io.Serializable;

import javax.persistence.*;


/** The persistent class for the user_configuration database table. */
@Entity
@Table(name = "user_configuration")

@NamedQueries({ 
   @NamedQuery(name = "UserConfigurationEntity.findAll", query = "SELECT u FROM UserConfigurationEntity u"), 
   @NamedQuery(name = "UserConfigurationEntity.findByUser", query = "SELECT u FROM UserConfigurationEntity u WHERE u.user = :user"), })
public class UserConfigurationEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   private String user;

   public UserConfigurationEntity() {
   }

   public String getUser() {
      return this.user;
   }

   public void setUser(String user) {
      this.user = user;
   }

}