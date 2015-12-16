package com.claro.docsisejb.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public abstract class AbstractDAO<T extends Serializable> {

   private Class<T> clase;

   @PersistenceContext(unitName = "DocSisPU")
   EntityManager entityManager;

   public final void setClase(final Class<T> entity) {
      this.clase = entity;
   }

   public T findOne(final long id) {
      return this.entityManager.find(this.clase, id);

   }

   public void create(final T entity) throws Exception {
      this.entityManager.persist(entity);
   }

   public T update(final T entity) {
      return this.entityManager.merge(entity);

   }

   public void delete(final T entity) {
      this.entityManager.remove(entity);
   }

   public void deleteById(final long entityId) {
      final T entity = this.findOne(entityId);
      this.delete(entity);
   }
}
