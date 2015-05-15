package com.claro.docsisejb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class Constante {

   private static Logger LOGGER = LogManager.getLogger(Constante.class.getName());

   public static final String PATH_DOCSIS_PROPERTIES = "C:/Julian/Claro/DocSis/config/docsis.properties";

   public static final String FILE_TEMPLATE_FILE_CFG = readProperties("FILE_TEMPLATE_FILE_CFG");

   public static final String CMD = "CMD";

   public static final String DOCSIS_EXECUTE = readProperties("DOCSIS_EXECUTE");

   public static final String KEY_FILE = readProperties("KEY_FILE");

   public static final String TAG_VPN_IDENTIFIER = readProperties("TAG_VPN_IDENTIFIER");

   public static final String TAG_S_VLAN = readProperties("TAG_S_VLAN");

   public static final String TAG_C_VLAN = readProperties("TAG_C_VLAN");

   public static final String TAG_ANCHO_BANDA_SUBIDA = readProperties("TAG_ANCHO_BANDA_SUBIDA");

   public static final String TAG_ANCHO_BANDA_BAJADA = readProperties("TAG_ANCHO_BANDA_BAJADA");

   public static final String NAME_FILE_OUT = readProperties("NAME_FILE_OUT");

   public static final String NAME_FILE_OUT_WITHOUT = readProperties("NAME_FILE_OUT_WITHOUT");

   public static final String FORMAT_FILE_OUT = readProperties("FORMAT_FILE_OUT");

   public static final String FORMAT_FILE_BIN = readProperties("FORMAT_FILE_BIN");

   public static final String URL_SALIR = "/DocSisWeb/";

   public static String readProperties(String name) {
      try {
         Properties properties = new Properties();
         InputStream input = new FileInputStream(PATH_DOCSIS_PROPERTIES);
         properties.load(input);

         return properties.getProperty(name);
      } catch (FileNotFoundException e) {
         LOGGER.error("Erro leyendo " + name, e);
      } catch (IOException e) {
         LOGGER.error("Erro leyendo " + name, e);
      }

      return "";
   }

}
