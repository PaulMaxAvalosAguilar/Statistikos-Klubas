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
package com.sdt.TrabajosViewer;

import com.sdt.Capabilities.CreatableTrabajosCapability;
import com.sdt.Capabilities.RefreshCapability;
import com.sdt.Datos.Trabajos;
import com.sdt.Datos.dao.TrabajosDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;

import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class TrabajosCapability implements Lookup.Provider {

    private Lookup lookup;
    private InstanceContent instanceContent = new InstanceContent();
    private static final Logger logger = Logger.getLogger(TrabajosCapability.class.getName());
    private final List<Trabajos> trabajoslist = new ArrayList<>();
    private TrabajosDao tdao = null;

    public TrabajosCapability(){
        lookup = new AbstractLookup(instanceContent);
        tdao = TrabajosDao.getInstance();
        if (tdao == null) {
            logger.log(Level.SEVERE, "Cannont get FamilyTreeManager object");
            LifecycleManager.getDefault().exit();
        }
        instanceContent.add(new RefreshCapability() {
            @Override
            public void refresh() throws IOException {
                if(tdao != null){
                    trabajoslist.clear();
                    trabajoslist.addAll(tdao.getAllRegistros());
                }else{
                    logger.log(Level.SEVERE, "Cannot get TrabajosDao");
                }
            }

        });
        
        instanceContent.add(new CreatableTrabajosCapability(){
            @Override
            public void create(Trabajos trabajo) throws IOException {
                if(tdao != null){
                    tdao.createRegistro(trabajo);
                }
            }           
        });
    }
    
    
    
    public List<Trabajos> getTrabajosList(){
        return trabajoslist;
    }

    @Override
    public Lookup getLookup() {
        return lookup;
    }

}
