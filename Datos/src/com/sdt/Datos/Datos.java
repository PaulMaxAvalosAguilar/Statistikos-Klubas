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
package com.sdt.Datos;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
@Entity(name = "Datos")
@Access(AccessType.PROPERTY)
public class Datos implements Serializable {

    public Datos() {

    }

    private IntegerProperty id;
    private int _id;

    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @Column(name = "inv_id")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    public int getId() {
        return (id == null) ? _id : id.get();
    }

    public void setId(int id) {
        if (this.id == null) {
            _id = id;
        } else {
            this.id.set(id);
        }
    }

    public IntegerProperty idProperty() {
        return (id == null) ? id = new SimpleIntegerProperty(this, "id", _id) : id;
    }

    private DoubleProperty numero;
    private double _numero;

    @Column
    public double getNumero() {
        return (numero == null) ? _numero : numero.get();
    }

    public void setNumero(Double numero) {
        if (this.numero == null) {
            _numero = numero;
        } else {
            this.numero.set(numero);
        }
    }

    public DoubleProperty numeroProperty() {
        return (numero == null) ? numero = new SimpleDoubleProperty(this, "numero", _numero) : numero;
    }

    
    private ObjectProperty<Trabajos> trabajo;
    private Trabajos _trabajo;

    
    @ManyToOne
    @JoinColumn(name = "trabajo")
    @Cascade(CascadeType.ALL)
    public Trabajos getTrabajo() {
        return (trabajo == null) ? _trabajo : trabajo.get();
    }

    public void setTrabajo(Trabajos trabajo) {
        if (this.trabajo == null) {
            _trabajo = trabajo;
        } else {
            this.trabajo.set(trabajo);
        }
    }

    public ObjectProperty<Trabajos> trabajoProperty() {
        return (trabajo == null) ? trabajo = new SimpleObjectProperty<Trabajos>(this, "trabajo", _trabajo) : trabajo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Datos other = (Datos) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sc = new StringBuilder();
        sc.append("El numero es: ").append(this.getNumero()).append(" del trabajo: ").append(
                this.getTrabajo());
        return sc.toString();
    }

}
