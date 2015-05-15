package com.claro.docsisejb.bean;

import javax.ejb.Remote;

import com.claro.docsisejb.dto.DocSisDTO;


@Remote
public interface AuditRemote {

   public void auditarGeneracionDocSis(DocSisDTO docsis, String usuario) throws Exception;

}
