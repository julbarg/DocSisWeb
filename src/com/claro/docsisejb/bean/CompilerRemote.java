package com.claro.docsisejb.bean;

import javax.ejb.Remote;

@Remote
public interface CompilerRemote {
   
   public String compilar(String nameFileOut) throws Exception;

}
