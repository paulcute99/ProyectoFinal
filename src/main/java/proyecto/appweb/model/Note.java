/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author Paul
 */
@Document(collection = "note")
public class Note {

    @Id
    private String id;
    
    private String periodo;

    @Field("nota1")
    private Float nota1;
    @Field("nota2")
    private Float nota2;
    @Field("nota3")
    private Float nota3;
    @Field("nota4")
    private Float nota4;
    @Field("nota5")
    private Float nota5;

    public Note() {
    }

    public String getId() {
        return id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public Float getNota1() {
        return nota1;
    }

    public Float getNota2() {
        return nota2;
    }

    public Float getNota3() {
        return nota3;
    }

    public Float getNota4() {
        return nota4;
    }

    public Float getNota5() {
        return nota5;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setNota1(Float nota1) {
        this.nota1 = nota1;
    }

    public void setNota2(Float nota2) {
        this.nota2 = nota2;
    }

    public void setNota3(Float nota3) {
        this.nota3 = nota3;
    }

    public void setNota4(Float nota4) {
        this.nota4 = nota4;
    }

    public void setNota5(Float nota5) {
        this.nota5 = nota5;
    }

    @Override
    public String toString() {
        return "Note{" + "id=" + id + ", periodo=" + periodo + ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3 + ", nota4=" + nota4 + ", nota5=" + nota5 + '}';
    }

}
