/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import proyecto.appweb.model.Course;

/**
 *
 * @author Paul
 */
public interface CourseRepository extends MongoRepository<Course, String> {

    Course findByNombre(String nombre);
    
    Course findFirstById(String id);
}
