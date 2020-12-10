/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.appweb.model.Course;
import proyecto.appweb.model.Student;
import proyecto.appweb.repository.StudentRep;

/**
 *
 * @author Paul
 */
@Service
public class StudentService {

    @Autowired
    private StudentRep studentRepository;

    public List<Student> listAllStudent() {
        return studentRepository.getAllStudents();
    }

    public List<Student> listStudentByCourseId(String id) {
        return studentRepository.getAllStudents(id);
    }

    public Student findStudentByDNI(String dni) {
        return studentRepository.findStudentByDNI(dni);
    }

    public Student findStudentById(String id) {
        return studentRepository.findStudentById(id);
    }

    public void saveStudent(Student student) {
        student.setNombre(student.getNombre());
        student.setApellido(student.getApellido());
        student.setDNI(student.getDNI());
        studentRepository.save(student);
    }

    public void deleteStudent(Student student) {
        studentRepository.deleteStudent(student);
    }

    public void addStudent(String id, Student student) {
        studentRepository.addStudent(id, student);
    }

    public void removeStudent(String id_course, Student student) {
        studentRepository.removeStudent(id_course, student);
    }

    public Course findCourseByName(String name) {
        return studentRepository.findCourseByName(name);
    }

    public void updateStudent(String id, Student student) {
        studentRepository.updateStudent(id,student);
    }
}
