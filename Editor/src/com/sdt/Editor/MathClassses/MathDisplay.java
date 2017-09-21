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
package com.sdt.Editor.MathClassses;

import com.sdt.Datos.Datos;
import com.sdt.Datos.Distribuciones;
import com.sdt.Datos.Frecuencias;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class MathDisplay {

    private final TextArea datosGarea;
    private TableView tablafrec;
    private TableView tabladist;
    private TableColumn<Frecuencias, Integer> notablafrec;
    private TableColumn<Frecuencias, String> limitablafrec;
    private TableColumn<Frecuencias, String> limstablafrec;
    private TableColumn<Frecuencias, String> fabstablafrec;
    private TableColumn<Frecuencias, String> freltablafrec;
    private TableColumn<Distribuciones, Integer> notabladist;
    private TableColumn<Distribuciones, String> valortabladist;
    private TableColumn<Distribuciones, String> dacumabstabladist;
    private TableColumn<Distribuciones, String> dacumreltabladist;

    private final List<Datos> list;
    private DescriptiveStatistics stats;

    private final String nombre;
    private final int decimales;
    private boolean ceil;

    //variables para charts
    private List<Double> valoresAc;
    private List<String> intervalos;
    private List<Double> valoresRel;

    private List<Double> distacAbs;
    private List<Double> distacRel;
    private List<String> intervalosdist;

    private boolean hayDatos;

    public MathDisplay(TextArea text, List<Datos> list,
            String nombre,
            int decimales,
            TableView tablafrec,
            TableColumn<Frecuencias, Integer> notablafrec,
            TableColumn<Frecuencias, String> limitablafrec,
            TableColumn<Frecuencias, String> limstablafrec,
            TableColumn<Frecuencias, String> fabstablafrec,
            TableColumn<Frecuencias, String> freltablafrec,
            TableView tabladist,
            TableColumn<Distribuciones, Integer> notabladist,
            TableColumn<Distribuciones, String> valortabladist,
            TableColumn<Distribuciones, String> dacumabstabladist,
            TableColumn<Distribuciones, String> dacumreltabladist) {
        this.datosGarea = text;
        this.list = list;
        this.nombre = nombre;
        this.decimales = decimales;
        this.tablafrec = tablafrec;
        this.notablafrec = notablafrec;
        this.limitablafrec = limitablafrec;
        this.limstablafrec = limstablafrec;
        this.fabstablafrec = fabstablafrec;
        this.freltablafrec = freltablafrec;
        this.notabladist = notabladist;
        this.valortabladist = valortabladist;
        this.dacumabstabladist = dacumabstabladist;
        this.dacumreltabladist = dacumreltabladist;
        this.tabladist = tabladist;
        calculate();

    }

    public void calculate() {
        if (!(list.isEmpty())) {

            this.hayDatos = true;

            int decimals = this.decimales;
            StringBuilder builder = new StringBuilder();
            builder.append("##");

            for (int i = 0; i < decimals; i++) {
                if (i == 0) {
                    builder.append(".");
                }
                builder.append("#");
            }
            DecimalFormat fN = new DecimalFormat(builder.toString());

            String textAppend = "";
            List<Double> modeList = new ArrayList();
            stats = new DescriptiveStatistics();
            int max_dem_lenght = 0;
            String[] splitter= Double.valueOf(0).toString().split("\\.");;
            for (int counter = 0; counter < list.size(); counter++) {

                double d = list.get(counter).getNumero();

                splitter = Double.valueOf(d).toString().split("\\.");
                if (splitter[1].length() > max_dem_lenght) {
                    max_dem_lenght = splitter[1].length();
                }
                stats.addValue(d);
                modeList.add(d);
            }

            StringBuilder sc = new StringBuilder();
            sc.append(".");
            if (splitter[1].length() >= 1) {
                for (int i = 0; i < splitter[1].length() - 1; i++) {
                    sc.append("0");
                }
            }
            sc.append("1");

            double decimalTablas = Double.parseDouble(sc.toString());

            double numero_valores = stats.getN();
            textAppend += String.format("El numero de valores es: %s\n", fN.format(numero_valores));

            double minValue = stats.getMin();
            double maxValue = stats.getMax();
            textAppend += String.format("El valor mas grande es: %s\nEl valor mas pequeño es: %s\n",
                    fN.format(maxValue), fN.format(minValue));

            double range = Math.abs(maxValue - minValue);
            textAppend += String.format("El valor del rango es: %s\n", fN.format(range));

            Double[] arr = modeList.toArray(new Double[modeList.size()]);
            List<Double> modes = getMode(arr);

            textAppend += String.format("\n\n");
            for (Double number : modes) {
                textAppend += String.format("La moda es %s\n", fN.format(number));
            }
            textAppend += String.format("\n\n");

            double mean = stats.getMean();
            textAppend += String.format("La media es: %s\n", fN.format(mean));

            double median = stats.getPercentile(50);
            textAppend += String.format("La mediana es: %s\n", fN.format(median));

            double variance = stats.getVariance();
            textAppend += String.format("La varianza es: %s\n", fN.format(variance));

            double standardDeviation = stats.getStandardDeviation();
            textAppend += String.format("La desviacion estandar es: %s\n",
                    fN.format(standardDeviation));

            double coefic_var = (standardDeviation / mean) * 100;
            textAppend += String.format("El coeficiente de variacion es de %s%%\n",
                    fN.format(coefic_var));

            double desviacion_aprox = range / 4;
            textAppend += String.format("El desviacion aproximada es de %s\n",
                    fN.format(desviacion_aprox));

            double sesgo = stats.getSkewness();
            textAppend += String.format("El sesgo es: %s\n", fN.format(sesgo));

            double reglaSesentayOchoBajo = mean - standardDeviation;
            textAppend += String.format("El limite inferior al 68%% es %s\n",
                    fN.format(reglaSesentayOchoBajo));

            double reglaSesentayOchoAlto = mean + standardDeviation;
            textAppend += String.format("El limite superior al 68%% es %s\n",
                    fN.format(reglaSesentayOchoAlto));

            double reglaNoventayCincoBajo = mean - (standardDeviation * 2);
            textAppend += String.format("El limite inferior al 95%% es %s\n",
                    fN.format(reglaNoventayCincoBajo));

            double reglaNoventayCincoAlto = mean + (standardDeviation * 2);
            textAppend += String.format("El limite superior al 95%% es %s\n",
                    fN.format(reglaNoventayCincoAlto));

            double reglaNoventaySieteBajo = mean - (standardDeviation * 3);
            textAppend += String.format("El limite inferior al 97%% es %s\n",
                    fN.format(reglaNoventaySieteBajo));

            double reglaNoventaySieteAlto = mean + (standardDeviation * 3);
            textAppend += String.format("El limite superior al 97%% es %s\n",
                    fN.format(reglaNoventaySieteAlto));

            textAppend += interpreta(sesgo, mean, median, standardDeviation, coefic_var);
            /*
            
            
            
            
            
            
            
            
            
             */
            //SEGUNDA PARTE
            /*
            
            
            
            
            
            
            
             */
            textAppend += String.format("\n\nAnalisis de frecuencias\n\n");
            int numero_clase;
            for (numero_clase = 0;; numero_clase++) {

                double square = Math.pow(2, numero_clase);
                textAppend += String.format("%d.- %.2f\n", numero_clase, square);
                if (square > numero_valores) {
                    textAppend += String.format("%.2f es mayor que el numero de datos\n", square);
                    break;

                }

            }

            textAppend += String.format("\nEl numero de clases es %d\n", numero_clase);

            /*
            
             */
            //Division decimal y gestion de redondeo
            BigDecimal rangedecimal = new BigDecimal(range);
            BigDecimal n_clasedecimal = new BigDecimal(numero_clase);

            BigDecimal largoClasedecimal = new BigDecimal(0);
            try {
                largoClasedecimal = rangedecimal.divide(n_clasedecimal);
            } catch (ArithmeticException ex) {
                largoClasedecimal = rangedecimal.divide(n_clasedecimal, 2, RoundingMode.CEILING);
                ceil = true;
            }

            double largoClase = largoClasedecimal.doubleValue();
            //Fin de division decimal y gestion de redondeo
            /*
            
            
             */
            textAppend += String.format("El largo de clase es %.2f\n", largoClase);

            //Listas para charts
            List<Double> valoresAc = new ArrayList<>();
            List<Double> valoresRel = new ArrayList<>();
            List<String> intervalos = new ArrayList<>();
            setFrecuenciaAbs(valoresAc);
            setFrecuenciaRel(valoresRel);
            setIntervalos(intervalos);

            final ObservableList<Frecuencias> datosFrec = FXCollections.observableArrayList();

            double limiteInferior = minValue;
            for (int i = 0; i < numero_clase; i++) {

                double limiteSuperior;
                double siguienteValor;
                double frecuencia;
                double relativa;
                Frecuencias frecuencias = new Frecuencias();

                if (i == numero_clase - 1) {
                    if (ceil == true) {
                        limiteSuperior = maxValue;
                        siguienteValor = limiteSuperior;
                        frecuencia = 0;
                        for (Double d : modeList) {
                            if ((d >= limiteInferior) && (d <= siguienteValor)) {
                                ++frecuencia;
                            }
                        }
                    } else {
                        limiteSuperior = limiteInferior + largoClase;
                        siguienteValor = limiteSuperior;
                        frecuencia = 0;
                        for (Double d : modeList) {
                            if ((d >= limiteInferior) && (d <= siguienteValor)) {
                                ++frecuencia;
                            }
                        }
                    }
                } else {
                    limiteSuperior = limiteInferior + largoClase;
                    siguienteValor = limiteSuperior - decimalTablas;
                    frecuencia = 0;
                    for (Double d : modeList) {
                        if ((d >= limiteInferior) && (d <= siguienteValor)) {
                            ++frecuencia;
                        }
                    }
                }
                //Concatenacion para graficacion
                String a = String.format("%.1f-", limiteInferior);
                String b = String.format("%.1f", limiteSuperior);
                relativa = (frecuencia / numero_valores) * 100;

                /*
                Set of values for display
                
                 */

                frecuencias.setNumero(i + 1);
                frecuencias.setFrecAbs(fN.format(frecuencia));
                frecuencias.setFrecRel(String.format("%s%%", fN.format(relativa)));
                frecuencias.setLimiteInferior(fN.format(limiteInferior));
                frecuencias.setSiguienteValor(fN.format(siguienteValor));
                valoresAc.add(frecuencia);
                valoresRel.add(relativa);
                datosFrec.add(frecuencias);
                StringBuilder sb = new StringBuilder();
                sb.append(a).append(b);
                intervalos.add(sb.toString());

                limiteInferior = limiteSuperior;
            }

            //asignacion de columnas
            notablafrec.setCellValueFactory(new PropertyValueFactory<Frecuencias, Integer>(
                    "numero"));
            limitablafrec.setCellValueFactory(new PropertyValueFactory<Frecuencias, String>(
                    "limiteInferior"));
            limstablafrec.setCellValueFactory(new PropertyValueFactory<Frecuencias, String>(
                    "siguienteValor"));
            fabstablafrec.setCellValueFactory(new PropertyValueFactory<Frecuencias, String>(
                    "frecAbs"));
            freltablafrec.setCellValueFactory(new PropertyValueFactory<Frecuencias, String>(
                    "frecRel"));


            /*
            
            
            
            
            
            
            
            
             */
            //TERCERA PARTE
            /*
            
            
            
            
            
            
            
             */
            //Listas para charts
            List<Double> distacAbs = new ArrayList<>();
            List<Double> distacRel = new ArrayList<>();
            List<String> intervalosdist = new ArrayList<>();
            setdistacAbs(distacAbs);
            setdistacRel(distacRel);
            setintervalosdist(intervalosdist);

            final ObservableList<Distribuciones> datosDistr = FXCollections.observableArrayList();

            double frecAc = 0;
            double frecRel = 0;
            limiteInferior = minValue;
            for (int i = 0; i < numero_clase + 1; i++) {
                double limiteSuperior = limiteInferior + largoClase;
                Distribuciones dist = new Distribuciones();

                if (i == 0) {
                    frecAc = 0;
                    frecRel = 0;
                    limiteInferior = minValue;

                } else if (i > 0) {
                    if (i == (numero_clase + 1) - 1) {
                        limiteInferior = maxValue;
                        frecAc += valoresAc.get(i - 1);
                        frecRel = (frecAc / numero_valores) * 100;
                    } else {
                        frecAc += valoresAc.get(i - 1);
                        frecRel = (frecAc / numero_valores) * 100;
                    }

                }

                
                dist.setNumero(i + 1);
                dist.setValor(fN.format(limiteInferior));
                dist.setDistribucionacum(fN.format(frecAc));
                dist.setDistribucionrelativa(String.format("%s%%", fN.format(frecRel)));
                distacRel.add(frecRel);
                distacAbs.add(frecAc);
                datosDistr.add(dist);

                StringBuilder sb = new StringBuilder();
                sb.append("<").append(String.format("%.2f", limiteInferior));
                intervalosdist.add(sb.toString());

                limiteInferior = limiteSuperior;
            }
            limiteInferior = minValue;

            notabladist.setCellValueFactory(new PropertyValueFactory<Distribuciones, Integer>(
                    "numero"));
            valortabladist.setCellValueFactory(new PropertyValueFactory<Distribuciones, String>(
                    "valor"));
            dacumabstabladist.setCellValueFactory(new PropertyValueFactory<Distribuciones, String>(
                    "distribucionacum"));
            dacumreltabladist.setCellValueFactory(new PropertyValueFactory<Distribuciones, String>(
                    "distribucionrelativa"));

            // COLOCA TEXTO
            final String textoThread = textAppend;
            Platform.runLater(() -> {
                tablafrec.setItems(datosFrec);
                tabladist.setItems(datosDistr);
                datosGarea.appendText(textoThread);
            });

        } else {
            this.hayDatos = false;

            Platform.runLater(() -> {
                datosGarea.appendText("No hay valores\n");
                tablafrec.getItems().clear();
                tabladist.getItems().clear();
            });

        }

    }

    private String interpreta(double sesgo, double mean, double median,
            double standardDeviation, double coefic_var) {
        boolean simetrico;

        String textAppend = "";
        textAppend += String.format("\n\nInterpretacion de la grafica\n\n");
        if ((sesgo > .5) || (sesgo < -.5)) {
            textAppend += String.format("1.-La grafica no es simetrica\n");
            simetrico = false;

            if (sesgo > .5) {
                textAppend += String.format("La grafica es asimetrica hacia la izquierda\n");
            } else if (sesgo < -.5) {
                textAppend += String.format("La grafica es asimetrica hacia la derecha\n");
            }
        } else {
            textAppend += String.format("1.-La grafica es simetrica\n");
            simetrico = true;
        }

        double rendimiento = (((mean - median) / median) * 100);
        textAppend += String.format("2.-El rendimiento media-mediana es de %.2f%%", rendimiento);
        if ((rendimiento > 5) || (rendimiento < -5)) {
            textAppend += String.format(" por lo tanto la media no es representativa\nporque su valor"
                    + " no es semejante al de la mediana por lo tanto no tipitfica al conjunto de datos\n");
        } else {
            textAppend += String.format(" por lo tanto la media es representativa\nporque su valor"
                    + " no es semejante al de la media por lo tanto tipifica al conjunto de datos\n");
        }

        if (coefic_var > 20) {
            textAppend += String.format("3.-En este caso la grafica es ancha....pues el valor del coeficiente\n"
                    + "de variacion es mayor a 20 y nuestros datos estan muy separados\n");
            if (simetrico == true) {
                textAppend += String.format("aun cuando \nnuestros datos son simetricos\n");
            }
        } else if (coefic_var <= 20) {
            textAppend += String.format("3.-En este caso la grafica NO es ancha....pues el valor del coeficiente\n"
                    + "de variacion es MENOr a 20\n");
        }

        if (simetrico == true) {
            textAppend += String.format("4.-La regla empirica se cumple porque la grafica es simetrica\n");
        } else {
            textAppend += String.format("4.-La regla empirica no se cumple porque la grafica no es simetrica\n");
        }

        textAppend += String.format("5.-La dispercion de mis datos con respecto a la media es de %.2f\n",
                standardDeviation);
        textAppend += String.format("Si yo tomo un dato cualquiera va a tener esa dispercion promedio\n");

        textAppend += String.format("6.- Si la desviacion estandar aproximada es muy distante de la  "
                + "desviacion estandar, la desviacion estandar\n aproximada no es confiable\n");

        return textAppend;
    }

    public List getMode(Double[] m) {
        HashMap<Double, Double> freqs = new HashMap<Double, Double>();
        for (double d : m) {
            Double freq = freqs.get(d);
            freqs.put(d, (freq == null ? 1 : freq + 1));
        }
        List<Double> mode = new ArrayList<Double>();
        List<Double> frequency = new ArrayList<Double>();
        List<Double> values = new ArrayList<Double>();

        for (Map.Entry<Double, Double> entry : freqs.entrySet()) {
            frequency.add(entry.getValue());
            values.add(entry.getKey());
        }

        if (!(frequency.isEmpty())) {
            double max = Collections.max(frequency);

            for (int i = 0; i < frequency.size(); i++) {
                double val = frequency.get(i);
                if (max == val) {
                    mode.add(values.get(i));
                }
            }
        }

        return mode;
    }

    private void setFrecuenciaAbs(List<Double> valoresAc) {
        this.valoresAc = valoresAc;
    }

    public List<Double> getFrecuenciaAbs() {
        return this.valoresAc;
    }

    private void setIntervalos(List<String> intervalos) {
        this.intervalos = intervalos;
    }

    public List<String> getIntervalos() {
        return intervalos;
    }

    private void setFrecuenciaRel(List<Double> valoresRel) {
        this.valoresRel = valoresRel;
    }

    public List<Double> getFrecuenciaRel() {
        return valoresRel;
    }

    private void setdistacAbs(List<Double> distacAbs) {
        this.distacAbs = distacAbs;
    }

    public List<Double> getdistacAbs() {
        return this.distacAbs;
    }

    private void setdistacRel(List<Double> distacRel) {
        this.distacRel = distacRel;
    }

    public List<Double> getdistacRel() {
        return this.distacRel;
    }

    private void setintervalosdist(List<String> intervalosdist) {
        this.intervalosdist = intervalosdist;
    }

    public List<String> getintervalosdist() {
        return this.intervalosdist;
    }

    public boolean hayDatos() {
        return this.hayDatos;
    }
}
