/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.appweb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proyecto.appweb.model.Student;
import proyecto.appweb.service.StudentService;

/**
 *
 * @author Paul
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/ver-cursos/alumnos/{id}")
    public String listStudents(@PathVariable("id") String id_curso, Model model, RedirectAttributes redirectAttrs) {

        Student student = new Student();
        List<Student> students = studentService.listStudentByCourseId(id_curso);
        model.addAttribute("student", student);
        model.addAttribute("students", students);
        model.addAttribute("id_curso", id_curso);
        return "ver-alumnos-id";
    }

    @RequestMapping(path = "/ver-alumnos")
    public String listAllStudents(Model model, RedirectAttributes redirectAttrs) {

        Student student = new Student();
        List<Student> students = studentService.listAllStudent();

        model.addAttribute("student", student);
        model.addAttribute("students", students);

        return "ver-alumnos";
    }

    @RequestMapping(path = "/save-student/{id_curso}")
    public String createNewStudentById(@PathVariable String id_curso, @ModelAttribute Student student, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        Student studentExist = studentService.findStudentByDNI(student.getDNI());
        System.out.print(id_curso);
        if (studentExist != null) {
            bindingResult
                    .rejectValue("DNI", "error.course",
                            "El DNI del estudiante ya existe");
            redirectAttrs
                    .addFlashAttribute("DNI", "El DNI del estudiante ya existe")
                    .addFlashAttribute("clase", "success");
        }
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("DNI", "error.student",
                            "El DNI del estudiante ya existe");
            return "redirect:/ver-cursos/alumnos/{id_curso}";
        } else {

            studentService.saveStudent(student);
            studentService.addStudent(id_curso, student);
        }

        return "redirect:/ver-cursos/alumnos/{id_curso}";
    }
    
    @RequestMapping(path = "/save-student")
    public String createNewStudent( @ModelAttribute Student student, BindingResult bindingResult, RedirectAttributes redirectAttrs) {
        Student studentExist = studentService.findStudentByDNI(student.getDNI());
        if (studentExist != null) {
            bindingResult
                    .rejectValue("DNI", "error.course",
                            "El DNI del estudiante ya existe");
            redirectAttrs
                    .addFlashAttribute("DNI", "El DNI del estudiante ya existe")
                    .addFlashAttribute("clase", "success");
        }
        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("DNI", "error.student",
                            "El DNI del estudiante ya existe");
            return "redirect:/ver-alumnos";
        } else {
            studentService.saveStudent(student);
        }
        return "redirect:/ver-alumnos";
    }

        @PostMapping(path = "/delete-student")
    public String deleteStudent( @ModelAttribute Student student, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        studentService.deleteStudent(student);

        return "redirect:/ver-alumnos";
    }
    
    @PostMapping(path = "/delete-student/{id_curso}")
    public String deleteStudentByCourse(@PathVariable String id_curso, @ModelAttribute Student student, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        studentService.removeStudent(id_curso, student);
        studentService.deleteStudent(student);

        return "redirect:/ver-cursos/alumnos/{id_curso}";
    }

}
