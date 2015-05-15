package com.claro.docsisejb.dto;

import java.io.Serializable;

public class DocSisDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 959538360396458003L;

   private String VPNIdentifier;

   private Integer SVLAN;

   private Integer CVLAN;

   private Integer anchoBandaSubida;

   private Integer anchoBandaBajada;

   private String macAddress;

   public DocSisDTO() {

   }

   public DocSisDTO(String VPNIdentifier, Integer SVLAN, Integer CVLAN, Integer anchoBandaSubida,
      Integer anchoBandaBajada, String macAddress) {
      this.VPNIdentifier = VPNIdentifier;
      this.SVLAN = SVLAN;
      this.CVLAN = CVLAN;
      this.anchoBandaSubida = anchoBandaSubida;
      this.anchoBandaBajada = anchoBandaBajada;
      this.macAddress = macAddress;
   }

   public String getVPNIdentifier() {
      return VPNIdentifier;
   }

   public void setVPNIdentifier(String vPNIdentifier) {
      VPNIdentifier = vPNIdentifier;
   }

   public Integer getSVLAN() {
      return SVLAN;
   }

   public void setSVLAN(Integer sVLAN) {
      SVLAN = sVLAN;
   }

   public Integer getCVLAN() {
      return CVLAN;
   }

   public void setCVLAN(Integer cVLAN) {
      CVLAN = cVLAN;
   }

   public Integer getAnchoBandaSubida() {
      return anchoBandaSubida;
   }

   public void setAnchoBandaSubida(Integer anchoBandaSubida) {
      this.anchoBandaSubida = anchoBandaSubida;
   }

   public Integer getAnchoBandaBajada() {
      return anchoBandaBajada;
   }

   public void setAnchoBandaBajada(Integer anchoBandaBajada) {
      this.anchoBandaBajada = anchoBandaBajada;
   }

   public String getMacAddress() {
      return macAddress;
   }

   public void setMacAddress(String macAddress) {
      this.macAddress = macAddress.toUpperCase();
   }

}
