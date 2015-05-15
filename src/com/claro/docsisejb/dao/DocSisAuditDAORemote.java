package com.claro.docsisejb.dao;

import javax.ejb.Remote;

import com.claro.docsisejb.entity.DocsisAuditEntity;

@Remote
public interface DocSisAuditDAORemote {
   
   public void create(DocsisAuditEntity docsisAudit) throws Exception;
   

}