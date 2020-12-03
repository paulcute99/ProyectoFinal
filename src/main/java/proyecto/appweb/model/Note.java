/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Paul
 */
@Document()
public class Note {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("nota")
    private Float nota;

    public Note() {
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getNota() {
        return nota;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", nombre=" + nombre + ", nota=" + nota + '}';
    }

}
