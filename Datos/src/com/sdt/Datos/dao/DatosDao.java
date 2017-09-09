/*
 * Copyright (C) 2017 Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.sdt.Datos.dao;

import com.sdt.Datos.Controllers.DatosJpaController;
import com.sdt.Datos.Controllers.exceptions.NonexistentEntityException;
import com.sdt.Datos.Datos;
import com.sdt.Datos.EManager.EManager;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class DatosDao {
    
    public static final String PROP_TRABAJOS_DESTROYED = "removeTrabajos";
    public static final String PROP_TRABAJOS_ADDED = "addTrabajos";
    public static final String PROP_TRABAJOS_UPDATED = "updateTrabajos";

    private PropertyChangeSupport propChangeSupport = null;
    
    private static EntityManagerFactory emf;
    private static DatosJpaController djpac;

    private static DatosDao instance;

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
    
    private DatosDao() {
        //private constructor singleton
    }

    public static DatosDao getInstance() {
        emf = EManager.getInstance().getEntityManagerFactory();
        djpac = new DatosJpaController(emf);
        if (instance == null) {
            instance = new DatosDao();
        }
        return instance;
        //instance initializer
        //Uses JPAController and local EntityManagerFactory fields
    }

    public void createRegistro(Datos d) {
        djpac.create(d);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_ADDED,null,d);
        
    }

    public void updateRegistro(Datos d) throws Exception {
        djpac.edit(d);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_UPDATED,null,d);
    }

    public void deleteRegistro(int i) throws NonexistentEntityException {
        djpac.destroy(i);
        getPropertyChangeSupport().firePropertyChange(TrabajosDao.PROP_TRABAJOS_DESTROYED,null,null);
    }

    public List<Datos> getAllRegistros() {
        return djpac.findDatosEntities();
    }

    public Datos getRegistro(int i) {
        return djpac.findDatos(i);
    }
    
    public int getNumeroRegistros(){
        return djpac.getDatosCount();
    }

    public EntityManager getEntityManager(){
        return djpac.getEntityManager();
    }
}
