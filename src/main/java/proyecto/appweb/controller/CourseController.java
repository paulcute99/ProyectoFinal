/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.appweb.model.Course;
import proyecto.appweb.service.CourseService;
import proyecto.appweb.service.StudentService;

/**
 *
 * @author Paul
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(path = "/ver-cursos")
    public String listCourse(Model model) {
        Course course = new Course();
        List<Course> courses = courseService.listCourse();
        model.addAttribute("curso", course);
        model.addAttribute("cursos", courses);
        return "ver-cursos";
    }

    @PostMapping(path = "/save")
    public String createNewCourse(Course course, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        Course courseExist = courseService.findCouserByName(course.getNombre());
        if (courseExist != null) {
            bindingResult
                    .rejectValue("nombre", "error.course",
                            "El nombre del curso ya existe");
            redirectAttrs
                    .addFlashAttribute("nombre", "El nombre del curso ya existe")
                    .addFlashAttribute("clase", "success");
        }
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("nombre", "error.course",
                            "El nombre del curso ya existe");
            return "redirect:/formularios-curso";
        } else {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El curso se ha guardado correctamen")
                    .addFlashAttribute("clase", "success");
            courseService.saveCourse(course);
        }

        return "redirect:/ver-cursos";
    }

    @PostMapping(path = "/delete/{id}")
    public String deleteCourse(@PathVariable String id, @ModelAttribute Course course, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        courseService.deleteCourse(courseService.findCouserById(id));

        return "redirect:/ver-cursos";
    }

    @PostMapping(path = "/edit/{id}")
    public String editAndSaveCourse(@PathVariable String id, @ModelAttribute @Valid Course course, Model model, BindingResult bindingResult) {
        System.out.print(id);
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Edit", "error.student",
                            "El DNI del estudiante ya existe");
            return "redirect:/formularios-curso/students/{id_curso}";
        } else {
            courseService.updateCourse(id, course);
        }
        return "redirect:/ver-cursos";
    }

    @RequestMapping(path = "/edit/{id}")
    public String editCourse(@PathVariable("id") String id_curso, Model model, RedirectAttributes redirectAttrs) {
        Course course = new Course();
        model.addAttribute("curso", course);
        model.addAttribute("id_curso", id_curso);
        return "editar-cursos";
    }

}
