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

import com.sdt.Capabilities.RefreshCapability;
import com.sdt.Datos.Trabajos;
import com.sdt.Datos.dao.TrabajosDao;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.LifecycleManager;

import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.WeakListeners;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class TrabajosChildFactory extends ChildFactory<Trabajos> {

    private TrabajosDao tdao;

    private final TrabajosCapability trabajosCapability = new TrabajosCapability();
    private static final Logger logger = Logger.getLogger(TrabajosChildFactory.class.getName());

    public TrabajosChildFactory() {
        this.tdao = TrabajosDao.getInstance();
        if (tdao == null) {
            logger.log(Level.SEVERE, "Cannot get TrabajosDao object");
            LifecycleManager.getDefault().exit();
        } else {
            tdao.addPropertyChangeListener(WeakListeners.propertyChange(trabajosListener, tdao));
        }

    }

    @Override
    protected boolean createKeys(List<Trabajos> toPopulate) {
        RefreshCapability refreshCapability
                = trabajosCapability.getLookup().lookup(RefreshCapability.class);
        if (refreshCapability != null) {
            try {
                refreshCapability.refresh();
                toPopulate.addAll(trabajosCapability.getTrabajosList());
                logger.log(Level.FINER, "create keys called: {0}", toPopulate);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
        return true;
    }

    @Override
    protected Node createNodeForKey(Trabajos key) {
        logger.log(Level.FINER, "createNodeForKey:{0}", key);
        TrabajosNode node = new TrabajosNode(key);
        return node;
    }

    private final PropertyChangeListener trabajosListener = (evt) -> {
        if (evt.getPropertyName().equals(TrabajosDao.PROP_TRABAJOS_ADDED)
                || evt.getPropertyName().equals(TrabajosDao.PROP_TRABAJOS_DESTROYED)) {
            this.refresh(true);
        }
    };

}
