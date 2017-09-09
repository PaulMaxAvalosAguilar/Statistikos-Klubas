/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdt.Capabilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.actions.Openable;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "OpenNodes",
        id = "com.sdt.Capabilities.SomeAction"
)
@ActionRegistration(
        iconBase = "com/sdt/Resources/if_icon-7-mail-envelope-open_314199.png",
        displayName = "#CTL_SomeAction"
)
@ActionReference(path = "Menu/File", position = 1450)
@Messages("CTL_SomeAction=OpenEditor")
public final class OpenAction implements ActionListener {

    private final Openable context;

    public OpenAction(Openable context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        context.open();
    }
}
