package com.claro.docsisejb.bean;

import java.rmi.RemoteException;

import javax.ejb.Remote;
import javax.xml.rpc.ServiceException;

import com.claro.docsisejb.dto.UsuarioDTO;

@Remote
public interface AutenticacionRemote {
   public UsuarioDTO autenticar(UsuarioDTO usuario) throws RemoteException, ServiceException;

}
