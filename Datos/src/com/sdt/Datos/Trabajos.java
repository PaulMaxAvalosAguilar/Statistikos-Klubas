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
import java.util.List;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Paul Max Avalos Aguilar at S.D.T. pauldromeasaurio@hotmail.com
 */
@Entity(name = "Trabajos")
@Access(AccessType.PROPERTY)
public class Trabajos implements Serializable {

    public Trabajos() {

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

    private StringProperty nombreTrabajo;
    private String _nombreTrabajo;

    @Column
    public String getNombreTrabajo() {
        return (nombreTrabajo == null) ? _nombreTrabajo : nombreTrabajo.get();
    }

    public void setNombreTrabajo(String nombreTrabajo) {
        if (this.nombreTrabajo == null) {
            _nombreTrabajo = nombreTrabajo;
        } else {
            this.nombreTrabajo.set(nombreTrabajo);
        }
    }

    public StringProperty nombreTrabajoProperty() {
        return (nombreTrabajo == null) ? nombreTrabajo = new SimpleStringProperty(this, "nombreTrabajo", _nombreTrabajo) : nombreTrabajo;
    }

    private ObjectProperty<List<Datos>> datos;
    private List<Datos> _datos;

    
    @OneToMany(mappedBy = "trabajo", orphanRemoval = true)
    @Cascade(CascadeType.ALL)
    public List<Datos> getDatos() {
        return (datos == null) ? _datos : datos.get();
    }

    public void setDatos(List<Datos> datos) {
        if (this.datos == null) {
            _datos = datos;
        } else {
            this.datos.set(datos);
        }
    }

    public ObjectProperty<List<Datos>> datosProperty() {
        return (datos == null) ? datos = new SimpleObjectProperty<List<Datos>>(this, "datos", _datos) : datos;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Trabajos other = (Trabajos) obj;
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
        sc.append(this.getNombreTrabajo());
        return sc.toString();
    }

}
