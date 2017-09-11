/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sdt.Datos;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
public class Frecuencias implements Serializable {

    public Frecuencias() {

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

    private StringProperty siguienteValor;
    private String _siguienteValor;

    public String getSiguienteValor() {
        return (siguienteValor == null) ? _siguienteValor : siguienteValor.get();
    }

    public void setSiguienteValor(String siguienteValor) {
        if (this.siguienteValor == null) {
            _siguienteValor = siguienteValor;
        } else {
            this.siguienteValor.set(siguienteValor);
        }
    }

    public StringProperty siguienteValorProperty() {
        return (siguienteValor == null) ? siguienteValor = new SimpleStringProperty(this, "siguienteValor", _siguienteValor) : siguienteValor;
    }

    private StringProperty limiteInferior;
    private String _limiteInferior;

    public String getLimiteInferior() {
        return (limiteInferior == null) ? _limiteInferior : limiteInferior.get();
    }

    public void setLimiteInferior(String limiteInferior) {
        if (this.limiteInferior == null) {
            _limiteInferior = limiteInferior;
        } else {
            this.limiteInferior.set(limiteInferior);
        }
    }

    public StringProperty limiteInferiorProperty() {
        return (limiteInferior == null) ? limiteInferior = new SimpleStringProperty(this, "limiteInferior", _limiteInferior) : limiteInferior;
    }

    private StringProperty frecAbs;
    private String _frecAbs;

    public String getFrecAbs() {
        return (frecAbs == null) ? _frecAbs : frecAbs.get();
    }

    public void setFrecAbs(String frecAbs) {
        if (this.frecAbs == null) {
            _frecAbs = frecAbs;
        } else {
            this.frecAbs.set(frecAbs);
        }
    }

    public StringProperty frecAbsProperty() {
        return (frecAbs == null) ? frecAbs = new SimpleStringProperty(this, "frecAbs", _frecAbs) : frecAbs;
    }

    private StringProperty frecRel;
    private String _frecRel;

    public String getFrecRel() {
        return (frecRel == null) ? _frecRel : frecRel.get();
    }

    public void setFrecRel(String frecRel) {
        if (this.frecRel == null) {
            _frecRel = frecRel;
        } else {
            this.frecRel.set(frecRel);
        }
    }

    public StringProperty frecRelProperty() {
        return (frecRel == null) ? frecRel = new SimpleStringProperty(this, "frecRel", _frecRel) : frecRel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Frecuencias other = (Frecuencias) obj;
        return Objects.equals(this.getNumero(), other.getNumero());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.getNumero());
        return hash;
    }
}
