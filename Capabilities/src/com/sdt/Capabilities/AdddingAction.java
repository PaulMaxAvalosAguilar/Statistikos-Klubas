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
        category = "AddingData",
        id = "com.sdt.Capabilities.AdddingAction"
)
@ActionRegistration(
        displayName = "#CTL_AdddingAction"
)
@Messages("CTL_AdddingAction=addData")
public final class AdddingAction implements ActionListener {

    private final AddingCapability context;

    public AdddingAction(AddingCapability context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        context.add();
    }
}
