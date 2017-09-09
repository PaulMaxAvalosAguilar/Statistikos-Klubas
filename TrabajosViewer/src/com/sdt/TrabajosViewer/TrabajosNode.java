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

import com.sdt.Capabilities.AddingCapability;
import com.sdt.Capabilities.RemovableTrabajosCapability;
import com.sdt.Capabilities.UpdateCapability;
import com.sdt.Datos.Controllers.exceptions.NonexistentEntityException;
import com.sdt.Datos.Datos;
import com.sdt.Datos.Trabajos;
import com.sdt.Datos.dao.DatosDao;
import com.sdt.Datos.dao.TrabajosDao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import org.netbeans.api.actions.Openable;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.actions.DeleteAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
@NbBundle.Messages({
    "HINT_TRABAJOS=Uno de tus proyectos",
    "Path_File=Path to file",
    "Path_Title=Load data",
    "File_Checking=File Checking"

})
public class TrabajosNode extends AbstractNode {

    private final InstanceContent instanceContent;
    private static final Logger logger = Logger.getLogger(TrabajosNode.class.getName());
    private TrabajosDao tdao = TrabajosDao.getInstance();

    public TrabajosNode(Trabajos trabajo) {
        this(trabajo, new InstanceContent());
    }

    public TrabajosNode(Trabajos trabajo, InstanceContent ic) {
        super(Children.LEAF, new AbstractLookup(ic));
        instanceContent = ic;
        instanceContent.add(trabajo);
        setName(String.valueOf(trabajo.getNombreTrabajo()));
        setDisplayName(trabajo.toString());
        setIconBaseWithExtension("com/sdt/TrabajosViewer/resources/if_database_export_16x16_9802.gif");
        setShortDescription(Bundle.HINT_TRABAJOS());

        instanceContent.add(new RemovableTrabajosCapability() {
            @Override
            public void remove(Trabajos trabajos) throws IOException {
                if (tdao != null) {

                    try {
                        tdao.deleteRegistro(trabajos.getId());
                    } catch (NonexistentEntityException ex) {
                        Exceptions.printStackTrace(ex);
                    }

                }
            }

        });

        instanceContent.add(new AddingCapability() {
            @Override
            public void add() {

                NotifyDescriptor.InputLine msg = new NotifyDescriptor.InputLine(Bundle.Path_File(),
                        Bundle.Path_Title());
                Object result = DialogDisplayer.getDefault().notify(msg);
                if (NotifyDescriptor.CANCEL_OPTION.equals(result)) {
                    return;
                }

                String file = msg.getInputText();
                //check for a zero lenght newName
                if (file.equals("")) {
                    return;
                }

                if (NotifyDescriptor.YES_OPTION.equals(result)) {
                    TopComponent tc = WindowManager.getDefault().findTopComponent(
                            "VistaTrabajosTopComponent");
                    Trabajos trabajo = null;
                    Datos dato;
                    DatosDao ddao = DatosDao.getInstance();

                    if (tc != null) {
                        Lookup lookup = tc.getLookup();
                        Collection<? extends Trabajos> todotrabajos = lookup.lookupAll(Trabajos.class);
                        trabajo = todotrabajos.iterator().next();
                    }
                    File f = new File(file);
                    if (f.exists()) {

                        FilePanel panel = new FilePanel();
                        DialogDescriptor dd = new DialogDescriptor(panel, Bundle.File_Checking());
                        if (DialogDisplayer.getDefault().notify(dd) == NotifyDescriptor.OK_OPTION) {
                            try {
                                String numero = "";
                                FileReader flE = new FileReader(f);
                                BufferedReader fE = new BufferedReader(flE);
                                while (numero != null) {
                                    numero = fE.readLine();
                                    if (numero != null) {

                                        dato = new Datos();
                                        dato.setNumero(numero);
                                        dato.setTrabajo(trabajo);
                                        ddao.updateRegistro(dato);
                                    }

                                }

                                fE.close();
                                flE.close();

                                NotifyDescriptor nd = new NotifyDescriptor.Message("Data added");
                                DialogDisplayer.getDefault().notify(nd);
                                WindowManager.getDefault().findTopComponent(
                                        "TrabajosEditorTopComponent").open();
                            } catch (FileNotFoundException ex) {
                                NotifyDescriptor nd = new NotifyDescriptor.Message(
                                        "The file was not found at runtime", NotifyDescriptor.ERROR_MESSAGE);
                                DialogDisplayer.getDefault().notify(nd);
                            } catch (IOException ex) {
                                NotifyDescriptor nd = new NotifyDescriptor.Message(
                                        "File could not be read", NotifyDescriptor.ERROR_MESSAGE);
                                DialogDisplayer.getDefault().notify(nd);
                                Logger.getLogger(TrabajosNode.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                NotifyDescriptor nd = new NotifyDescriptor.Message(
                                        "File could not be read", NotifyDescriptor.ERROR_MESSAGE);
                                DialogDisplayer.getDefault().notify(nd);
                                Logger.getLogger(TrabajosNode.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    } else {
                        NotFilePanel npanel = new NotFilePanel();
                        DialogDescriptor dd = new DialogDescriptor(npanel, Bundle.File_Checking());
                        if (DialogDisplayer.getDefault().notify(dd) == NotifyDescriptor.OK_OPTION) {

                        }
                    }

                }
            }
        });

        instanceContent.add(new Openable() {
            @Override
            public void open() {
                WindowManager.getDefault().findTopComponent("TrabajosEditorTopComponent").open();
            }

        });

        instanceContent.add(new UpdateCapability() {
            @Override
            public void update() {
                NotifyDescriptor.InputLine msg = new NotifyDescriptor.InputLine("Modify the name",
                        "Update workspace");

                TopComponent tc = WindowManager.getDefault().findTopComponent(
                        "VistaTrabajosTopComponent");
                String update = null;
                Trabajos trabajo = null;
                Lookup lookup = tc.getLookup();
                Collection<? extends Trabajos> todotrabajos = lookup.lookupAll(Trabajos.class);
                trabajo = todotrabajos.iterator().next();
                msg.setInputText(trabajo.getNombreTrabajo());
                Object result = DialogDisplayer.getDefault().notify(msg);
                
                
                if (NotifyDescriptor.CANCEL_OPTION.equals(result)) {
                    return;
                }

                String file = msg.getInputText();
                //check for a zero lenght newName
                if (file.equals("")) {
                    return;
                }

                if (NotifyDescriptor.YES_OPTION.equals(result)) {
                    trabajo.setNombreTrabajo(msg.getInputText());
                    TrabajosDao tdao = TrabajosDao.getInstance();
                    try {
                        tdao.updateRegistro(trabajo);
                    } catch (Exception ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            }

        });
    }

    public Action[] getActions(boolean context) {
        List<Action> trabajosActions = new ArrayList<>(Arrays.asList(super.getActions(context)));
        trabajosActions.add(DeleteAction.get(DeleteAction.class));
        trabajosActions.addAll(Utilities.actionsForPath("Actions/AddingData"));
        trabajosActions.addAll(Utilities.actionsForPath("Actions/OpenNodes"));
        trabajosActions.addAll(Utilities.actionsForPath("Actions/Update"));
        return trabajosActions.toArray(new Action[trabajosActions.size()]);
    }

    @Override
    public Action getPreferredAction() {
        List<Action> actions = new ArrayList<>(Utilities.actionsForPath("Actions/OpenNodes"));
        if (!actions.isEmpty()) {
            return actions.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean canDestroy() {
        return true;
    }

    @Override
    public void destroy() throws IOException {
        final RemovableTrabajosCapability doremove = getLookup().lookup(
                RemovableTrabajosCapability.class);
        final Trabajos trabajos = getLookup().lookup(Trabajos.class);
        if (doremove != null && trabajos != null) {
            doremove.remove(trabajos);
        }
    }

}
