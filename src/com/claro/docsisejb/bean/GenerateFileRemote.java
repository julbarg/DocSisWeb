package com.claro.docsisejb.bean;

import javax.ejb.Remote;

import com.claro.docsisejb.dto.DocSisDTO;

@Remote
public interface GenerateFileRemote {
   public String createFileDocSis(DocSisDTO docsis);

   public String getFileTotal();

   public String cargarExpresionRegular();
}
