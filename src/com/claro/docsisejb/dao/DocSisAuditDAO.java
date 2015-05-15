package com.claro.docsisejb.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.claro.docsisejb.entity.DocsisAuditEntity;

@Stateless
@LocalBean
public class DocSisAuditDAO extends AbstractDAO<DocsisAuditEntity> implements DocSisAuditDAORemote {

}
