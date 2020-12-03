/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import com.mongodb.client.result.UpdateResult;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import proyecto.appweb.model.Course;
import proyecto.appweb.model.Student;

/**
 *
 * @author Paul
 */
@Repository
public class StudentRep {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Student findStudentByDNI(String dni) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("DNI").is(dni)), Student.class);
    }
    
    public Course findCourseByName(String name){
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("name").is(name)), Course.class);
    }

    public Student save(Student student) {
        return mongoTemplate.save(student);
    }

    public List<Student> getAllStudents() {
        return mongoTemplate.findAll(Student.class);
    }

    public List<Student> getAllStudents(String id) {

        return mongoTemplate.findDistinct(new Query().addCriteria(Criteria.where("id").is(id)), "estudiantes", Course.class, Student.class);

    }

    public Student findStudentById(String id) {
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(id)), Student.class);
    }

    public void deleteStudent(Student student) {
        mongoTemplate.remove(student);
    }

    public UpdateResult addStudent(String id, Student student) {
        return mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("id").is(id)),
                new Update().addToSet("estudiantes", student),
                Course.class);
    }

    public UpdateResult removeStudent(String id_course, Student student) {
        return mongoTemplate.updateFirst(
                new Query().addCriteria(Criteria.where("id").is(id_course)),
                new Update().pull("estudiantes", student),
                Course.class);
    }
}
