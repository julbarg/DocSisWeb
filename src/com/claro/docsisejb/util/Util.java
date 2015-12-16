package com.claro.docsisejb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Util {

   private static Logger LOGGER = LogManager.getLogger(Util.class.getName());

   public static final String PATH_DOCSIS_DATA = "PATH_DOCSIS_DATA";

   public static final String PATH_DOCSIS_CONFIG = "PATH_DOCSIS_CONFIG";

   public static String toStringAHex(String text) {
      return String.format("%x", new BigInteger(1, text.getBytes(Charset.defaultCharset())));
   }

   public static String toIntegerAHex(Integer integer) {
      String hex = Integer.toHexString(integer);
      hex = StringUtils.leftPad(hex, 4, '0');
      return hex;
   }

   public static String toBps(Integer integer) {
      long bps = integer * 1000000;
      return "" + bps;
   }

   public static String generateFileConfig(String macAddress) {
      macAddress = replaceAddress(macAddress);
      String nameFileOut = Constante.NAME_FILE_OUT + macAddress.toUpperCase();
      return nameFileOut;
   }

   public static String generatePathFileConfig(String macAddress) {
      macAddress = replaceAddress(macAddress);
      String nameFileOut = Constante.NAME_FILE_OUT + macAddress.toUpperCase() + Constante.FORMAT_FILE_OUT;
      String pathFileConfig = Constante.readProperties(PATH_DOCSIS_DATA) + nameFileOut;
      return pathFileConfig;
   }

   private static String replaceAddress(String macAddress) {
      try {
         macAddress = macAddress.replaceAll(":", "");
      } catch (Exception e) {
      }
      try {
         macAddress = macAddress.replaceAll("-", "");
      } catch (Exception e) {
      }
      return macAddress;
   }

   public static String generatePathFileConfigCMD(String macAddress) {
      String nameFileOut = macAddress.toUpperCase() + Constante.FORMAT_FILE_OUT + " ";
      String pathFileConfig = readProperties(PATH_DOCSIS_DATA) + nameFileOut;
      return pathFileConfig;
   }

   public static String generatePathFileBinCMD(String nameFileOut) {
      nameFileOut = replaceAddress(nameFileOut);
      nameFileOut = nameFileOut.replaceAll(Constante.NAME_FILE_OUT_WITHOUT, Constante.NAME_FILE_OUT);
      String nameFileOutBin = nameFileOut.toUpperCase() + Constante.FORMAT_FILE_BIN + " ";
      String pathFileBin = readProperties(PATH_DOCSIS_DATA) + nameFileOutBin;
      return pathFileBin;
   }

   public static String generatePathFileBinCMDWithoutSpace(String nameFileOut) {
      nameFileOut = replaceAddress(nameFileOut);
      nameFileOut = nameFileOut.replaceAll(Constante.NAME_FILE_OUT_WITHOUT, Constante.NAME_FILE_OUT);
      String nameFileOutBin = nameFileOut.toUpperCase() + Constante.FORMAT_FILE_BIN;
      String pathFileBin = readProperties(PATH_DOCSIS_DATA) + nameFileOutBin;
      return pathFileBin.trim();
   }

   public static String generateNameFileBinCMD(String nameFileOut) {
      String nameFileOutBin = Constante.NAME_FILE_OUT + nameFileOut.toUpperCase() + Constante.FORMAT_FILE_BIN + " ";
      return nameFileOutBin;
   }

   public static String generatePathTemplateFile() {
      String pathTemplateFile = readProperties(PATH_DOCSIS_CONFIG) + Constante.FILE_TEMPLATE_FILE_CFG;
      return pathTemplateFile;
   }

   public static String generartePathKeyFile() {
      String pathKeyFile = readProperties(PATH_DOCSIS_CONFIG) + Constante.KEY_FILE + " ";
      return pathKeyFile;

   }

   public static String readProperties(String name) {
      try {
         Properties properties = new Properties();
         InputStream input = new FileInputStream(Constante.PATH_DOCSIS_PROPERTIES);
         properties.load(input);

         return properties.getProperty(name);
      } catch (FileNotFoundException e) {
         LOGGER.error("Error leyendo " + name, e);
      } catch (IOException e) {
         LOGGER.error("Error leyendo " + name, e);
      }

      return "";
   }

   public static int readPropertiesInt(String name) {
      try {
         Properties properties = new Properties();
         InputStream input = new FileInputStream(Constante.PATH_DOCSIS_PROPERTIES);
         properties.load(input);

         return Integer.parseInt(properties.getProperty(name));
      } catch (FileNotFoundException e) {
         LOGGER.error("Error leyendo " + name, e);
      } catch (IOException e) {
         LOGGER.error("Error leyendo " + name, e);
      }

      return 0;
   }

   public static void writeProperties(String name, String value) {
      try {
         Properties properties = new Properties();
         InputStream input = new FileInputStream(Constante.PATH_DOCSIS_PROPERTIES);
         properties.load(input);
         properties.setProperty(name, value);
         File f = new File(Constante.PATH_DOCSIS_PROPERTIES);
         OutputStream out = new FileOutputStream(f);
         properties.store(out, "Archivo de Propiedades Modificado por el Usuario");
      } catch (Exception e) {
         LOGGER.error("Error escribiendo archivo de propiedades", e);
      }
   }

}
