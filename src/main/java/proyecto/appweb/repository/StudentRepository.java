/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import proyecto.appweb.model.Student;

/**
 *
 * @author Paul
 */
public interface StudentRepository extends MongoRepository<Student, String> {

    Student findFirstById(String id);

    Student findByDNI(String dni);

    @Query("nestedObjectProperty._id : { $id : ?0}")
    List<Student> findStudentsByIdCourse(String id);

}
