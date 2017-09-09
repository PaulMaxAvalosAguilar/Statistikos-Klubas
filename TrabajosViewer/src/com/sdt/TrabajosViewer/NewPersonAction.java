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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "NewNode",
        id = "com.sdt.TrabajosViewer.NewPersonAction"
)
@ActionRegistration(
        iconBase = "com/sdt/TrabajosViewer/resources/Add.png",
        displayName = "#CTL_NewPersonAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
    ,
  @ActionReference(path = "Toolbars/File", position = 300)
})
@Messages("CTL_NewPersonAction=New Workspace")
public final class NewPersonAction implements ActionListener {

    private final TrabajosType context;

    public NewPersonAction(TrabajosType context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            context.create();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
