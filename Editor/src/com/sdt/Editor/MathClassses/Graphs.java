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
import java.util.List;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;


/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */

public class Graphs {



    public Graphs() {

    }

    public void displayLineChart(LineChart<Number, Number> linechart, List<Datos> list,
            String nombre) {

        linechart.getData().clear();
        linechart.setCreateSymbols(true);

        //El titulo debe de ser modificado
        linechart.setTitle(nombre);
        linechart.getXAxis().setLabel("No. de dato");
        linechart.getYAxis().setLabel("Valor de dato");

        XYChart.Series series;
        series = new XYChart.Series();
        series.setName("Serie de datos");

        for (int counter = 0; counter < list.size(); counter++) {
            String name = String.valueOf(counter + 1);
            double d = list.get(counter).getNumero();
            series.getData().add(new XYChart.Data(name, d));
        }

        //displays Data
        linechart.getData().add(series);
    }

    public void displayAbsFreqHistogram(BarChart<String, Number> histograma, List<Double> list,
        List<String> intervalos, TextField titulo, TextField ejex, TextField ejey, 
        TextField serie) {

        histograma.getData().clear();
        histograma.titleProperty().bindBidirectional(titulo.textProperty());
        histograma.getXAxis().labelProperty().bindBidirectional(ejex.textProperty());
        histograma.getYAxis().labelProperty().bindBidirectional(ejey.textProperty());

        XYChart.Series series;
        series = new XYChart.Series();
        series.nameProperty().bindBidirectional(serie.textProperty());

        for (int counter = 0; counter < list.size(); counter++) {
            double d = list.get(counter);
            series.getData().add(new XYChart.Data(intervalos.get(counter), d));
        }

        histograma.getData().add(series);

    }
    
    public void displayRelFreqHistograma(BarChart<String, Number> histograma, List<Double> list,
        List<String> intervalos, TextField titulo, TextField ejex, TextField ejey, 
        TextField serie){

        
        histograma.getData().clear();
        
        histograma.titleProperty().bindBidirectional(titulo.textProperty());
        histograma.getXAxis().labelProperty().bindBidirectional(ejex.textProperty());
        histograma.getYAxis().labelProperty().bindBidirectional(ejey.textProperty());

        XYChart.Series series;
        series = new XYChart.Series();
        series.nameProperty().bindBidirectional(serie.textProperty());

        for (int counter = 0; counter < list.size(); counter++) {
            double d = list.get(counter);
            series.getData().add(new XYChart.Data(intervalos.get(counter), d));
        }

        histograma.getData().add(series);
        
    }
    
    public void displaydistAcAbs(LineChart<Number, Number> linechart, List<Double> list,
        List<String> intervalos, TextField titulo, TextField ejex, TextField ejey, 
        TextField serie){
        

        
        linechart.getData().clear();
        
        linechart.titleProperty().bindBidirectional(titulo.textProperty());
        linechart.getXAxis().labelProperty().bindBidirectional(ejex.textProperty());
        linechart.getYAxis().labelProperty().bindBidirectional(ejey.textProperty());

        XYChart.Series series;
        series = new XYChart.Series();
        series.nameProperty().bindBidirectional(serie.textProperty());

        for (int counter = 0; counter < list.size(); counter++) {
            double d = list.get(counter);
            series.getData().add(new XYChart.Data(intervalos.get(counter), d));
        }

        linechart.getData().add(series);
        
    }
    
    public void displaydistAcRel(LineChart<Number, Number> linechart, List<Double> list,
        List<String> intervalos, TextField titulo, TextField ejex, TextField ejey, 
        TextField serie){
        

        
        linechart.getData().clear();
        
        linechart.titleProperty().bindBidirectional(titulo.textProperty());
        linechart.getXAxis().labelProperty().bindBidirectional(ejex.textProperty());
        linechart.getYAxis().labelProperty().bindBidirectional(ejey.textProperty());

        XYChart.Series series;
        series = new XYChart.Series();
        series.nameProperty().bindBidirectional(serie.textProperty());

        for (int counter = 0; counter < list.size(); counter++) {
            double d = list.get(counter);
            series.getData().add(new XYChart.Data(intervalos.get(counter), d));
        }

        linechart.getData().add(series);
    }

}
