/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdt.Datos;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class Distribuciones implements Serializable {

    public Distribuciones() {

    }

    private IntegerProperty numero;
    private int _numero;

    public int getNumero() {
        return (numero == null) ? _numero : numero.get();
    }

    public void setNumero(int numero) {
        if (this.numero == null) {
            _numero = numero;
        } else {
            this.numero.set(numero);
        }
    }

    public IntegerProperty numeroProperty() {
        return (numero == null) ? numero = new SimpleIntegerProperty(this, "numero", _numero) : numero;
    }

    private StringProperty valor;
    private String _valor;

    public String getValor() {
        return (valor == null) ? _valor : valor.get();
    }

    public void setValor(String valor) {
        if (this.valor == null) {
            _valor = valor;
        } else {
            this.valor.set(valor);
        }
    }

    public StringProperty valorProperty() {
        return (valor == null) ? valor = new SimpleStringProperty(this, "valor", _valor) : valor;
    }

    private StringProperty distribucionacum;
    private String _distribucionacum;

    public String getDistribucionacum() {
        return (distribucionacum == null) ? _distribucionacum : distribucionacum.get();
    }

    public void setDistribucionacum(String distribucionacum) {
        if (this.distribucionacum == null) {
            _distribucionacum = distribucionacum;
        } else {
            this.distribucionacum.set(distribucionacum);
        }
    }

    public StringProperty distribucionacumProperty() {
        return (distribucionacum == null) ? distribucionacum = new SimpleStringProperty(this, "distribucionacum", _distribucionacum) : distribucionacum;
    }

    private StringProperty distribucionrelativa;
    private String _distribucionrelativa;

    public String getDistribucionrelativa() {
        return (distribucionrelativa == null) ? _distribucionrelativa : distribucionrelativa.get();
    }

    public void setDistribucionrelativa(String distribucionrelativa) {
        if (this.distribucionrelativa == null) {
            _distribucionrelativa = distribucionrelativa;
        } else {
            this.distribucionrelativa.set(distribucionrelativa);
        }
    }

    public StringProperty distribucionrelativaProperty() {
        return (distribucionrelativa == null) ? distribucionrelativa = new SimpleStringProperty(this, "distribucionrelativa", _distribucionrelativa) : distribucionrelativa;
    }
}
