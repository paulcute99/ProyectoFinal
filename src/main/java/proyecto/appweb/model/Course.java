/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import proyecto.appweb.model.Student    ;

/**
 *
 * @author Paul
 */
@Document(collection = "course")
public class Course {

    @Id
    private String id;
    
    private String nombre;

    private String seccion;
   
    
    private List<Student> estudiantes = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }


    public void setEstudiantes(List<Student> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Student> getEstudiantes() {
        return estudiantes;
    }

    public String getSeccion() {
        return seccion;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", nombre=" + nombre + ", seccion=" + seccion + ", estudiantes=" + estudiantes + '}';
    }

}
