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
import proyecto.appweb.model.Note;
import proyecto.appweb.service.NoteService;

/**
 *
 * @author Paul
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(path = "/formularios-curso/notes/{id}")
    public String listNotes(@PathVariable("id") String id_estudiante, Model model, RedirectAttributes redirectAttrs) {

        Note note = new Note();
        List<Note> notes = noteService.listNote(id_estudiante);

        // Course course = studentService.findCourseByName("");
        model.addAttribute("nota", note);
        model.addAttribute("notas", notes);
        model.addAttribute("id_estudiante", id_estudiante);
        return "notas";
    }

    @RequestMapping(path = "/formularios-curso/notes")
    public String listAllNotes(Model model, RedirectAttributes redirectAttrs) {

        Note note = new Note();
        List<Note> notes = noteService.listAllNotes();

        model.addAttribute("nota", note);
        model.addAttribute("notas", notes);
        return "AllNotas";
    }

    @RequestMapping(path = "/save-note/{id_estudiante}")
    public String createNewNote(@PathVariable String id_estudiante, @ModelAttribute Note note, BindingResult bindingResult, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {
            System.out.print(bindingResult.toString());
            bindingResult
                    .rejectValue("Nota", "error.note",
                            "No se  ha podido guardar la nota del estudiante");
            return "redirect:/formularios-curso/students/{id_curso}";
        } else {

            noteService.saveNote(note);
            noteService.save(id_estudiante, note);
        }

        return "redirect:/formularios-curso/notes/{id_estudiante}";
    }

    @PostMapping(path = "/delete-note/{id_estudiante}")
    public String deleteNote(@PathVariable String id_estudiante, @ModelAttribute Note note, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        noteService.removeNote(id_estudiante, note);
        noteService.deleteNote(note);
        

        return "redirect:/formularios-curso/notes/{id_estudiante}";
    }
    
     @PostMapping(path = "/delete-note")
    public String deleteNotes( @ModelAttribute Note note, Model model, RedirectAttributes redirectAttrs) {

        redirectAttrs
                .addFlashAttribute("mensaje", "Se ha eleminado correctamente")
                .addFlashAttribute("clase", "warning");
        noteService.deleteNote(note);
        

        return "redirect:/formularios-curso/notes";
    }
}