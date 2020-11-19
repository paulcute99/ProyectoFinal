/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.appweb.model.Course;
import proyecto.appweb.service.CourseService;

/**
 *
 * @author Paul
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "/formularios-curso")
    public String listCourse(Model model) {
        Course course = new Course();
        List<Course> courses = courseService.listCourse();
        model.addAttribute("curso", course);
        model.addAttribute("cursos", courses);
        return "formularios-curso";
    }

    @PostMapping(value = "/save")
    public String createNewCourse(Course course, BindingResult bindingResult) {
        Course courseExist = courseService.findCouserByName(course.getNombre());
        if (courseExist != null) {
            bindingResult
                    .rejectValue("nombre", "error.course",
                            "El nombre del curso ya existe");
        }
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("nombre", "error.course",
                            "El nombre del curso ya existe");
            return "redirect:/formularios-curso";
        } else {
            courseService.saveCourse(course);
        }

        return "redirect:/formularios-curso";
    }

    @PostMapping(path = "/delete")
    public String deleteCourse(@ModelAttribute Course course, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        courseService.deleteCourse(course.getId());

        return "redirect:/formularios-curso";
    }
    
    @PostMapping(path = "/edit")
    public String editCourse(@ModelAttribute Course course, Model model) {
        Course courseExistent = courseService.findCouserById(course.getId());
        courseService.saveCourse(courseExistent);
        return "redirect:/formularios-curso";
    }

}
