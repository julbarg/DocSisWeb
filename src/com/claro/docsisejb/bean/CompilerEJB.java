package com.claro.docsisejb.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.claro.docsisejb.util.Constante;
import com.claro.docsisejb.util.Util;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


@Stateless
@LocalBean
public class CompilerEJB implements CompilerRemote {

   private static Logger LOGGER = LogManager.getLogger(CompilerEJB.class.getName());
   
   private String nameFileOut;

   private String comandCMD;

   private String cmdOut;
   
   @EJB
   private SFTRemote sftpEJB;

   @Override
   public String compilar(String nameFileOut) throws Exception {
      this.nameFileOut = nameFileOut;
      generateComand();
      executeComand();
      try{
         validateBin();
      }catch (FileNotFoundException e){
         String mensaje = "No se genero el archivo compilado"+
            "\n Comando ejecutado: "+comandCMD+
            "\n Respuesta de consola: "+cmdOut;
         LOGGER.error("Validando compilacion", e);
         throw new FileNotFoundException(mensaje);
      }
      
      try{
         enviar();
      }catch (Exception e){
         String mensaje = "Ha ocuurido un error al intentar subir el archivo al servidor";
         LOGGER.error("Error enviando archivo por FTP", e);
         throw new FileNotFoundException(mensaje);
      }
      return cmdOut;
   }

   private void enviar() throws FileNotFoundException, JSchException, SftpException {
      sftpEJB.upLoad(Util.generatePathFileBinCMD(nameFileOut));
   }
   
   private FileReader validateBin() throws FileNotFoundException {
      String nameFile = Util.generatePathFileBinCMD(nameFileOut);
      File file = new File(nameFile);
      FileReader fileReader = new FileReader(file);
     
      return fileReader;
      
   }

   private void generateComand() {
      StringBuffer comand = new StringBuffer();
      comand.append(Constante.readProperties(Constante.CMD));
      comand.append(Constante.DOCSIS_EXECUTE);
      comand.append(Util.generatePathFileConfigCMD(nameFileOut));
      comand.append(Util.generartePathKeyFile());
      comand.append(Util.generatePathFileBinCMD(nameFileOut));

      comandCMD = comand.toString();
   }

   private void executeComand() throws IOException {
      Process process = Runtime.getRuntime().exec(comandCMD);
      BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line = null;
      StringBuffer cmdOutBuffer = new StringBuffer();
      while ((line = in.readLine()) != null) {
         cmdOutBuffer.append(line);
      }
      cmdOut = cmdOutBuffer.toString();
   }

   
}
