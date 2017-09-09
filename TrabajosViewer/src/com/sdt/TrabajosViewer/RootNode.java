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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import org.openide.actions.NewAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.actions.SystemAction;
import org.openide.util.datatransfer.NewType;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */

@NbBundle.Messages({
    "HINT_RootNode=Show workspaces",
    "LBL_RootNode=Home Workspaces"
})
public class RootNode extends AbstractNode{
    
    
    private final TrabajosType trabajosType = new TrabajosType();
    
    private final InstanceContent instanceContent;
    public RootNode(){
        this(new InstanceContent());
    }
    
    
    
    private RootNode(InstanceContent ic){
        super(Children.create(new TrabajosChildFactory(), false),new AbstractLookup(ic));
        instanceContent =ic;
        setIconBaseWithExtension("com/sdt/TrabajosViewer/resources/home.png");
        setDisplayName(Bundle.LBL_RootNode());
        setShortDescription(Bundle.HINT_RootNode());
        
        instanceContent.add(new RefreshCapability(){
            @Override
            public void refresh() throws IOException {
                setChildren(Children.create(new TrabajosChildFactory(), false));
            }            
        });
        
        instanceContent.add(trabajosType);
    }

    @Override
    public Action[] getActions(boolean context) {
        List<Action> actions = new ArrayList<>(Arrays.asList(super.getActions(context)));
        actions.addAll(Utilities.actionsForPath("Actions/RootNode"));
        actions.add(SystemAction.get(NewAction.class));
        actions.addAll(Utilities.actionsForPath("Action/NewNode"));
        return actions.toArray(new Action[actions.size()]);
    }
    
    @Override
    public NewType[] getNewTypes(){
        return new NewType[]{trabajosType};
    }
    
}
