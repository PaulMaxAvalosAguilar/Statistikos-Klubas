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
import com.sdt.Datos.Trabajos;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.*;
import org.openide.util.NbBundle.Messages;
import org.openide.util.datatransfer.NewType;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
@Messages({
    "LBL_NewName=New name:",
    "TITLE_NewWork=New workspace"
})
public class TrabajosType extends NewType {

    private final TrabajosCapability trabajosCapability = new TrabajosCapability();
    private static final Logger logger = Logger.getLogger(TrabajosType.class.getName());

    @Override
    public String getName() {
        return Bundle.TITLE_NewWork();
    }

    @Override
    public void create() throws IOException {
        NotifyDescriptor.InputLine msg = new NotifyDescriptor.InputLine(Bundle.LBL_NewName(),
                Bundle.TITLE_NewWork());
        Object result = DialogDisplayer.getDefault().notify(msg);
        if (NotifyDescriptor.CANCEL_OPTION.equals(result)) {
            return;
        }

        String newName = msg.getInputText();
        //check for a zero lenght newName
        if (newName.equals("")) {
            return;
        }

        if (NotifyDescriptor.YES_OPTION.equals(result)) {
            final Trabajos trabajo = new Trabajos();
            trabajo.setNombreTrabajo(newName);
            final CreatableTrabajosCapability cpc = trabajosCapability.getLookup().lookup(
                    CreatableTrabajosCapability.class);
            if(cpc != null){
                cpc.create(trabajo);
                logger.log(Level.FINER,"Creating trabajo {0}", trabajo);
            }
        }

    }

}
