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
package com.sdt.Editor.FXControllers;


import com.sdt.Datos.Datos;
import com.sdt.Datos.Distribuciones;
import com.sdt.Datos.Frecuencias;
import com.sdt.Datos.Trabajos;
import com.sdt.Datos.dao.DatosDao;
import com.sdt.Editor.MathClassses.Graphs;
import com.sdt.Editor.MathClassses.MathDisplay;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class MainController implements Initializable {

    //Primera vista
    @FXML
    private TextArea datosGarea;
    @FXML
    private TableView tablafrec;
    @FXML
    private TableColumn<Frecuencias, Integer> notablafrec;
    @FXML
    private TableColumn<Frecuencias, String> limitablafrec;
    @FXML
    private TableColumn<Frecuencias, String> limstablafrec;
    @FXML
    private TableColumn<Frecuencias, String> fabstablafrec;
    @FXML
    private TableColumn<Frecuencias, String> freltablafrec;
    @FXML
    private TableView tabladist;
    @FXML
    private TableColumn<Distribuciones, Integer> notabladist;
    @FXML
    private TableColumn<Distribuciones, String> valortabladist;
    @FXML
    private TableColumn<Distribuciones, String> dacumabstabladist;
    @FXML
    private TableColumn<Distribuciones, String> dacumreltabladist;

    //Grafico de linea
    @FXML
    private LineChart<Number, Number> lineChart;

    //Histograma de frecuencia acumulada absoluta
    @FXML
    private BarChart<String, Number> histograma;
    @FXML
    private TextField titulo1;
    @FXML
    private TextField ejex1;
    @FXML
    private TextField ejey1;
    @FXML
    private TextField serie1;
    @FXML
    private Label fabslabel;
    @FXML
    private Button valoresfabs;
    @FXML
    private Button etiquetafabs;

    //Histograma de frecuencia relativa
    @FXML
    private BarChart<String, Number> histogramarel;
    @FXML
    private TextField titulo2;
    @FXML
    private TextField ejex2;
    @FXML
    private TextField ejey2;
    @FXML
    private TextField serie2;
    @FXML
    private Label frellabel;
    @FXML
    private Button valoresfrel;
    @FXML
    private Button etiquetafrel;

    //distribucion acumulada absoluta
    @FXML
    private LineChart<Number, Number> dacuma;
    @FXML
    private TextField titulo3;
    @FXML
    private TextField ejex3;
    @FXML
    private TextField ejey3;
    @FXML
    private TextField serie3;
    @FXML
    private Label dacumalabel;
    @FXML
    private Button valoresdacuma;
    @FXML
    private Button etiquetadacuma;

    //distribucion acumulada relativa
    @FXML
    private LineChart<Number, Number> dacumr;
    @FXML
    private TextField titulo4;
    @FXML
    private TextField ejex4;
    @FXML
    private TextField ejey4;
    @FXML
    private TextField serie4;
    @FXML
    private Label dacumrlabel;
    @FXML
    private Button valoresdacumr;
    @FXML
    private Button etiquetadacumr;

    
    private Trabajos trabajo;
    private int numero;
    String nombre = "";

    private List<Datos> list;
    private DatosDao ddao;

    private MathDisplay display;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Histograma absoluto copia
        /*
        
        
        
         */
        valoresfabs.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getFrecuenciaAbs().size(); i++) {
                        copia += String.format("%s\n", display.getFrecuenciaAbs().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        etiquetafabs.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getIntervalos().size(); i++) {
                        copia += String.format("%s\n", display.getIntervalos().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        //Histograma relativo copia
        /*
        
        
        
         */
        valoresfrel.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getFrecuenciaRel().size(); i++) {
                        copia += String.format("%s\n", display.getFrecuenciaRel().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        etiquetafrel.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getIntervalos().size(); i++) {
                        copia += String.format("%s\n", display.getIntervalos().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        //Distribucion absouluta copia
        /*
        
        
        
         */
        valoresdacuma.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getdistacAbs().size(); i++) {
                        copia += String.format("%s\n", display.getdistacAbs().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        etiquetadacuma.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getintervalosdist().size(); i++) {
                        copia += String.format("%s\n", display.getintervalosdist().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        //Distribucion relativa copia
        /*
        
        
        
         */
        valoresdacumr.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getdistacRel().size(); i++) {
                        copia += String.format("%s\n", display.getdistacRel().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

        etiquetadacumr.setOnAction((event) -> {
            if (display != null) {
                if (display.hayDatos()) {
                    final Clipboard clipboard = Clipboard.getSystemClipboard();
                    final ClipboardContent content = new ClipboardContent();
                    String copia = "";
                    for (int i = 0; i < display.getintervalosdist().size(); i++) {
                        copia += String.format("%s\n", display.getintervalosdist().get(i));
                    }
                    content.putString(copia);
                    clipboard.setContent(content);
                }
            }
        });

    }

    public void doUpdate(Collection<? extends Trabajos> trabajos) {
        if (trabajos.isEmpty()) {
            return;
        }

        trabajo = trabajos.iterator().next();
        numero = trabajo.getId();
        nombre = trabajo.getNombreTrabajo();

        ddao = DatosDao.getInstance();
        EntityManager man = ddao.getEntityManager();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT c FROM Datos c WHERE c.trabajo.id = ");
        sb.append(numero);
        list = man.createQuery(sb.toString()).getResultList();

        datosGarea.setText("");
        calculate(2);
    }

    public void hayValores() {
        datosGarea.setText("Hay valores");
    }

    public void hayValoresErr() {
        datosGarea.setText("Hay valores con errores");
    }

    private void calculate(int decimals) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                MathDisplay mat = new MathDisplay(datosGarea, list, nombre, decimals, tablafrec,
                        notablafrec, limitablafrec, limstablafrec, fabstablafrec, freltablafrec,
                        tabladist, notabladist, valortabladist, dacumabstabladist,
                        dacumreltabladist);
                display = mat;

                Graphs graphdisplay = new Graphs();
                graphdisplay.displayLineChart(lineChart, list, nombre);

                Platform.runLater(() -> {
                    fabslabel.setText(nombre);
                    frellabel.setText(nombre);
                    dacumalabel.setText(nombre);
                    dacumrlabel.setText(nombre);
                });

                if (mat.hayDatos()) {
                    graphdisplay.displayAbsFreqHistogram(histograma, mat.getFrecuenciaAbs(),
                            mat.getIntervalos(), titulo1, ejex1, ejey1, serie1);

                    graphdisplay.displayRelFreqHistograma(histogramarel, mat.getFrecuenciaRel(),
                            mat.getIntervalos(), titulo2, ejex2, ejey2, serie2);
                    graphdisplay.displaydistAcAbs(dacuma, mat.getdistacAbs(), mat.getintervalosdist(),
                            titulo3, ejex3, ejey3, serie3);
                    graphdisplay.displaydistAcRel(dacumr, mat.getdistacRel(), mat.getintervalosdist(),
                            titulo4, ejex4, ejey4, serie4);
                } else {
                    Platform.runLater(() -> {
                        lineChart.getData().clear();
                        histograma.getData().clear();
                        histogramarel.getData().clear();
                        dacuma.getData().clear();
                        dacumr.getData().clear();
                    });

                }
                return null;
            }

        };
        new Thread(task).start();

    }
}
