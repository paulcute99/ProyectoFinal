/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.service;

import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.stereotype.Service;
import proyecto.appweb.model.Student;
import proyecto.appweb.repository.CourseRep;
import proyecto.appweb.repository.StudentRepository;

/**
 *
 * @author Paul
 */
@Service
public class StudentService {

    @Autowired
    private CourseRep courseRep;

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listStudent() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public List<Student> listStudentByCourseId(String id) {
        List<Student> students = studentRepository.findStudentsByIdCourse(id);
        return students;
    }

    public Student findStudentByDNI(String dni) {
        return studentRepository.findByDNI(dni);
    }

    public Student findStudentById(String id) {
        return studentRepository.findFirstById(id);
    }

    public void saveStudent(String id, Student student) {
        student.setNombre(student.getNombre());
        student.setApellido(student.getApellido());
        student.setDNI(student.getDNI());
        studentRepository.save(student);
    }

    public UpdateResult saveStudentByid(String id, Student student) {
        return courseRep.saveStudentByid(id, student);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }
}
