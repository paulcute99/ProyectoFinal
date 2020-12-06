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
import proyecto.appweb.repository.CourseRepository;

/**
 *
 * @author Paul
 */
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course findCouserByName(String nombre) {
        return courseRepository.findByName(nombre);
    }

    public Course findCouserById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> listCourse() {
        List<Course> courses = courseRepository.getAllCourse();
        return courses;
    }

    public void saveCourse(Course course) {
        course.setNombre(course.getNombre());
        course.setSeccion(course.getSeccion());
        courseRepository.save(course);
    }

    public void deleteCourse(Course course) {
        courseRepository.deleteCourse(course);
    }
    
    

}
