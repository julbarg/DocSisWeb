package com.claro.docsisejb.bean;

import java.io.FileNotFoundException;

import javax.ejb.Remote;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;


@Remote
public interface SFTRemote {
   
   public void upLoad(String fileNamePath) throws JSchException, SftpException, FileNotFoundException;
   
}
