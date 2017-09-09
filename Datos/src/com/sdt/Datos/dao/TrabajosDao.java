/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdt.Datos.dao;

import com.sdt.Datos.Controllers.TrabajosJpaController;
import com.sdt.Datos.Controllers.exceptions.NonexistentEntityException;
import com.sdt.Datos.EManager.EManager;
import com.sdt.Datos.Trabajos;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class TrabajosDao {
     public static final String PROP_TRABAJOS_DESTROYED = "removeTrabajos";
    public static final String PROP_TRABAJOS_ADDED = "addTrabajos";
    public static final String PROP_TRABAJOS_UPDATED = "updateTrabajos";

    private PropertyChangeSupport propChangeSupport = null;

    private static EntityManagerFactory emf;
    private static TrabajosJpaController tjpac;
    
    private static TrabajosDao instance;

    private PropertyChangeSupport getPropertyChangeSupport() {
        if (this.propChangeSupport == null) {
            this.propChangeSupport = new PropertyChangeSupport(this);
        }

        return this.propChangeSupport;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }

    private TrabajosDao() {
        //private constructor singleton
    }

    public static TrabajosDao getInstance() {
        emf = EManager.getInstance().getEntityManagerFactory();
        tjpac = new TrabajosJpaController(emf);
        if (instance == null) {
            instance = new TrabajosDao();
        }
        return instance;
        //instance initializer
        //Uses JPAController and local EntityManagerFactory fields
    }

    public void createRegistro(Trabajos t) {
        tjpac.create(t);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_ADDED,null,t);
    }

    public void updateRegistro(Trabajos t) throws Exception {
        tjpac.edit(t);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_UPDATED,null,t);
    }

    public void deleteRegistro(int i) throws NonexistentEntityException {
        tjpac.destroy(i);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_DESTROYED,null,null);
    }

    public List<Trabajos> getAllRegistros() {
        return tjpac.findTrabajosEntities();
    }

    public Trabajos getRegistro(int i) {
        return tjpac.findTrabajos(i);
    }

    public int getNumeroRegistros() {
        return tjpac.getTrabajosCount();
    }

    public EntityManager getEntityManager() {
        return tjpac.getEntityManager();
    }
}
