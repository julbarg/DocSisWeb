package com.claro.docsisejb.entity;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/** The persistent class for the docsis_audit database table. */
@Entity
@Table(
   name = "docsis_audit")
@NamedQuery(
   name = "DocsisAudit.findAll", query = "SELECT d FROM DocsisAuditEntity d")
public class DocsisAuditEntity implements Serializable {
   private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(
      strategy = GenerationType.AUTO)
   private int id;

   @Column(
      name = "ancho_bajada")
   private String anchoBajada;

   @Column(
      name = "ancho_subida")
   private String anchoSubida;

   @Column(
      name = "c_vlan")
   private String cVlan;

   @Temporal(TemporalType.DATE)
   private Date fecha;

   @Column(
      name = "mac_address")
   private String macAddress;

   @Column(
      name = "nombre_archivo")
   private String nombreArchivo;

   @Column(
      name = "s_vlan")
   private String sVlan;

   private String usuario;

   @Column(
      name = "vpn_identifier")
   private String vpnIdentifier;

   public DocsisAuditEntity() {
      
   }  

   public int getId() {
      return this.id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getAnchoBajada() {
      return this.anchoBajada;
   }

   public void setAnchoBajada(String anchoBajada) {
      this.anchoBajada = anchoBajada;
   }

   public String getAnchoSubida() {
      return this.anchoSubida;
   }

   public void setAnchoSubida(String anchoSubida) {
      this.anchoSubida = anchoSubida;
   }

   public String getCVlan() {
      return this.cVlan;
   }

   public void setCVlan(String cVlan) {
      this.cVlan = cVlan;
   }

   public Date getFecha() {
      return this.fecha;
   }

   public void setFecha(Date fecha) {
      this.fecha = fecha;
   }

   public String getMacAddress() {
      return this.macAddress;
   }

   public void setMacAddress(String macAddress) {
      this.macAddress = macAddress;
   }

   public String getNombreArchivo() {
      return this.nombreArchivo;
   }

   public void setNombreArchivo(String nombreArchivo) {
      this.nombreArchivo = nombreArchivo;
   }

   public String getSVlan() {
      return this.sVlan;
   }

   public void setSVlan(String sVlan) {
      this.sVlan = sVlan;
   }

   public String getUsuario() {
      return this.usuario;
   }

   public void setUsuario(String usuario) {
      this.usuario = usuario;
   }

   public String getVpnIdentifier() {
      return this.vpnIdentifier;
   }

   public void setVpnIdentifier(String vpnIdentifier) {
      this.vpnIdentifier = vpnIdentifier;
   }

}