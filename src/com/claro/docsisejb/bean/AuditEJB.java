package com.claro.docsisejb.bean;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.claro.docsisejb.dao.DocSisAuditDAORemote;
import com.claro.docsisejb.dto.DocSisDTO;
import com.claro.docsisejb.entity.DocsisAuditEntity;
import com.claro.docsisejb.util.Util;


@Stateless
@LocalBean
public class AuditEJB implements AuditRemote {

   @EJB
   private DocSisAuditDAORemote auditDAO;

   @Override
   public void auditarGeneracionDocSis(DocSisDTO docsis, String usuario) throws Exception {

      DocsisAuditEntity docsisAudit = new DocsisAuditEntity();
      docsisAudit.setVpnIdentifier(docsis.getVPNIdentifier());
      docsisAudit.setCVlan(docsis.getCVLAN().toString());
      docsisAudit.setSVlan(docsis.getSVLAN().toString());
      docsisAudit.setAnchoBajada(docsis.getAnchoBandaBajada().toString());
      docsisAudit.setAnchoSubida(docsis.getAnchoBandaSubida().toString());
      docsisAudit.setMacAddress(docsis.getMacAddress());

      docsisAudit.setNombreArchivo(Util.generateNameFileBinCMD(docsis.getMacAddress()));
      docsisAudit.setFecha(new Date());
      docsisAudit.setUsuario(usuario);

      auditDAO.create(docsisAudit);

   }

}
