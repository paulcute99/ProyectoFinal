/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import proyecto.appweb.model.Course;

/**
 *
 * @author Paul
 */
@Repository
public class CourseRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
 
    public Course findByName(String nombre){
       return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("nombre").is(nombre)), Course.class);
    }
    
    public Course save(Course course){
        return mongoTemplate.save(course);
    }
    
    public List<Course> getAllCourse(){
        return mongoTemplate.findAll(Course.class);
    }
    
    public void UpdateCourse(Course course){
        mongoTemplate.save(course);
    }
    
    public Course findById(String id){
        return mongoTemplate.findOne(new Query().addCriteria(Criteria.where("id").is(id)), Course.class);
    }
    
    public void deleteCourse(Course course){
        mongoTemplate.remove(course);
    }

    public void update(String id, Course course) {
        Update update = new Update();
        update.set("nombre", course.getNombre());
        update.set("seccion", course.getSeccion());
       mongoTemplate.updateFirst(new Query().addCriteria(Criteria.where("id").is(id)),update , Course.class);
    }
}
