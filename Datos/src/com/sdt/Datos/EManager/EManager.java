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
package com.sdt.Datos.EManager;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class EManager {
    private static EntityManagerFactory em = Persistence.createEntityManagerFactory("aplicacion");
    private static EManager instance;
    
    
    private EManager(){
        //Singleton class
    }

    
    
    public static EManager getInstance(){
        return (instance == null)? instance = new EManager(): instance;
        //Instance initializer
    }
    
    public EntityManagerFactory getEntityManagerFactory(){
        //EntityManagerFactory accesor
        return em;
    }

    
    public void destroyManagerFactory() {
    em.close();
    }
}