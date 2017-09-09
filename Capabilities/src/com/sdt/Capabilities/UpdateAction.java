/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdt.Capabilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Update",
        id = "com.sdt.Capabilities.UpdateAction"
)
@ActionRegistration(
        displayName = "#CTL_UpdateAction"
)
@Messages("CTL_UpdateAction=update name")
public final class UpdateAction implements ActionListener {

    private final UpdateCapability context;

    public UpdateAction(UpdateCapability context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        context.update();
    }
}
