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
import proyecto.appweb.model.Note;



/**
 *
 * @author Paul
 */
@Document(collection = "student")
public class Student {

    @Id
    private String id;

    @Field("nombre")
    private String nombre;

    @Field("apellido")
    private String apellido;

    @Field("DNI")
    private String DNI;

    @Field("notes")
    private List<Note> notes = new ArrayList<>();

    public void setNotes(List<Note> note) {
        this.notes = note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDNI() {
        return DNI;
    }

    public List<Note> getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", DNI=" + DNI + ", note=" + notes + '}';
    }

}
