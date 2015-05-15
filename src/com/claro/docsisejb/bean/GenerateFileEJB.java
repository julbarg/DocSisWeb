package com.claro.docsisejb.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.claro.docsisejb.dto.DocSisDTO;
import com.claro.docsisejb.dto.FTPDTO;
import com.claro.docsisejb.util.Constante;
import com.claro.docsisejb.util.Util;


@Stateless
@LocalBean
public class GenerateFileEJB implements GenerateFileRemote {

   private static Logger LOGGER = LogManager.getLogger(GenerateFileEJB.class.getName());
   
   private String fileTotal;

   private String nameFileOut;

   public String getFileTotal() {
      return fileTotal;
   }

   public void setFileTotal(String fileTotal) {
      this.fileTotal = fileTotal;
   }

   @Override
   public String createFileDocSis(DocSisDTO docsis) {
      try {
         readTemplateFile();
         replaceFile(docsis);
         writeFile(docsis);
         return nameFileOut;
      } catch (IOException e) {
         LOGGER.error("Error generando Archivo DocSis", e);
         return null;
      }

   }

   private void readTemplateFile() throws IOException {
      String nameFile = Util.generatePathTemplateFile();
      File file = new File(nameFile);
      FileReader fileReader = new FileReader(file);
      BufferedReader buffer = new BufferedReader(fileReader);
      String line;
      StringBuffer templateFile = new StringBuffer();
      while ((line = buffer.readLine()) != null) {
         templateFile.append(line);
         templateFile.append("\n");
      }
      buffer.close();

      fileTotal = templateFile.toString();

   }

   private void replaceFile(DocSisDTO docsis) {

      String VPN = Util.toStringAHex(docsis.getVPNIdentifier());
      String CVLAN = Util.toIntegerAHex(docsis.getCVLAN());
      String SVLAN = Util.toIntegerAHex(docsis.getSVLAN());
      String anchoBandaSubida = Util.toBps(docsis.getAnchoBandaSubida());
      String anchoBandaBajada = Util.toBps(docsis.getAnchoBandaBajada());

      fileTotal = fileTotal.replaceAll(Constante.TAG_VPN_IDENTIFIER.trim(), VPN)
         .replaceAll(Constante.TAG_S_VLAN.trim(), CVLAN).replaceAll(Constante.TAG_C_VLAN.trim(), SVLAN)
         .replaceAll(Constante.TAG_ANCHO_BANDA_SUBIDA.trim(), anchoBandaSubida)
         .replaceAll(Constante.TAG_ANCHO_BANDA_BAJADA.trim(), anchoBandaBajada);
   }

   private void writeFile(DocSisDTO docSis) throws FileNotFoundException {
      File fileOut;
      PrintWriter writer;
      nameFileOut = Util.generateFileConfig(docSis.getMacAddress());

      fileOut = new File(Util.generatePathFileConfig(docSis.getMacAddress()));
      writer = new PrintWriter(fileOut);
      writer.print(fileTotal);
      writer.close();

   }

   public String getNameFileOut() {
      return nameFileOut;
   }

   public void setNameFileOut(String nameFileOut) {
      this.nameFileOut = nameFileOut;
   }

   @Override
   public String cargarExpresionRegular() {
      return Util.readProperties(FTPDTO.EXPRESION_REGULAR);
   }
}
